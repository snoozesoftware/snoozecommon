package org.inria.myriads.snoozecommon.parser.api.impl;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.inria.myriads.libvirt.domain.LibvirtConfigDomain;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring.NetworkDemand;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineTemplate;
import org.inria.myriads.snoozecommon.globals.Globals;

import junit.framework.TestCase;


public class TestLibvirtConfigParser extends TestCase 
{

    
    LibvirtConfigParser parser_;
    
    @Override
    protected void setUp() throws Exception 
    {
       parser_ = new LibvirtConfigParser();
    }
    
    
    /**
     * Parses from raw templates (kvm).
     * @throws Exception 
     */
    public void testParseDescriptionFromRawTemplateKVM() throws Exception
    {
        
            //load file
            FileInputStream inputStream = new FileInputStream("src/test/resources/templates/debian1.xml");
            String xml = IOUtils.toString(inputStream);
            System.out.println(xml);
            inputStream.close();
            // generates virtual machine template
            VirtualMachineTemplate virtualMachineDescription = new VirtualMachineTemplate();
            virtualMachineDescription.setLibVirtTemplate(xml);
            NetworkDemand networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
            networkDemand.setRxBytes(12500);
            networkDemand.setTxBytes(12600);
            VirtualMachineMetaData metaData ;
            metaData = parser_.parseDescription(virtualMachineDescription);
            assertEquals("debian1",metaData.getVirtualMachineLocation().getVirtualMachineId());
            assertEquals(1.0, metaData.getRequestedCapacity().get(Globals.CPU_UTILIZATION_INDEX));
            assertEquals(128000.0, metaData.getRequestedCapacity().get(Globals.MEMORY_UTILIZATION_INDEX));
            assertEquals(12500.0, metaData.getRequestedCapacity().get(Globals.NETWORK_RX_UTILIZATION_INDEX));
            assertEquals(12600.0, metaData.getRequestedCapacity().get(Globals.NETWORK_TX_UTILIZATION_INDEX));
    }
    
    /**
     * Parses from raw templates (xen).
     * @throws Exception 
     */
    public void testParseDescriptionFromRawTemplateXEN() throws Exception
    {
        
            //load file
            FileInputStream inputStream = new FileInputStream("src/test/resources/templates/debian1.xen.xml");
            String xml = IOUtils.toString(inputStream);
            System.out.println(xml);
            inputStream.close();
            // generates virtual machine template
            VirtualMachineTemplate virtualMachineDescription = new VirtualMachineTemplate();
            virtualMachineDescription.setLibVirtTemplate(xml);
            NetworkDemand networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
            networkDemand.setRxBytes(12500);
            networkDemand.setTxBytes(12600);
            VirtualMachineMetaData metaData ;
            metaData = parser_.parseDescription(virtualMachineDescription);
            assertEquals("debian1",metaData.getVirtualMachineLocation().getVirtualMachineId());
            assertEquals(1.0, metaData.getRequestedCapacity().get(Globals.CPU_UTILIZATION_INDEX));
            assertEquals(128000.0, metaData.getRequestedCapacity().get(Globals.MEMORY_UTILIZATION_INDEX));
            assertEquals(12500.0, metaData.getRequestedCapacity().get(Globals.NETWORK_RX_UTILIZATION_INDEX));
            assertEquals(12600.0, metaData.getRequestedCapacity().get(Globals.NETWORK_TX_UTILIZATION_INDEX));
    }
    
    /**
     * Parses Description from thin template.
     * 
     */
    public void testParseDescriptionFromThinTemplate()
    {
        VirtualMachineTemplate virtualMachineDescription = new VirtualMachineTemplate();
        virtualMachineDescription.setImageId("/tmp/test.img");
        virtualMachineDescription.setName("test");
        virtualMachineDescription.setVcpus(2);
        virtualMachineDescription.setMemory(512000);
        
        NetworkDemand networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
        networkDemand.setRxBytes(12500);
        networkDemand.setTxBytes(12600);
        VirtualMachineMetaData metaData ;
        try
        {
            metaData = parser_.parseDescription(virtualMachineDescription);
            assertEquals("test",metaData.getVirtualMachineLocation().getVirtualMachineId());
            assertEquals(2.0, metaData.getRequestedCapacity().get(Globals.CPU_UTILIZATION_INDEX));
            assertEquals(512000.0, metaData.getRequestedCapacity().get(Globals.MEMORY_UTILIZATION_INDEX));
            assertEquals(12500.0, metaData.getRequestedCapacity().get(Globals.NETWORK_RX_UTILIZATION_INDEX));
            assertEquals(12600.0, metaData.getRequestedCapacity().get(Globals.NETWORK_TX_UTILIZATION_INDEX));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Test the mac address replacement.
     */
    public void testReplaceMacAddress()
    {
        String expectedMacAddress =  "12:12:12:12:12:12";
        VirtualMachineTemplate virtualMachineDescription = new VirtualMachineTemplate();
        virtualMachineDescription.setImageId("/tmp/test.img");
        virtualMachineDescription.setName("test");
        virtualMachineDescription.setVcpus(2);
        virtualMachineDescription.setMemory(512000);
        
        NetworkDemand networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
        networkDemand.setRxBytes(12500);
        networkDemand.setTxBytes(12600);
        VirtualMachineMetaData metaData ;
        try
        {
            metaData = parser_.parseDescription(virtualMachineDescription);
            String newXml = parser_.replaceMacAddressInTemplate(metaData.getXmlRepresentation(), expectedMacAddress);
            //deserialize the xml
            JAXBContext context = JAXBContext.newInstance(LibvirtConfigDomain.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            InputStream input = new ByteArrayInputStream(newXml.getBytes());
            LibvirtConfigDomain domain = (LibvirtConfigDomain) jaxbUnmarshaller.unmarshal(input);
            String effectiveMacAddress = domain.getDevices().getInterfaces().get(0).getMacAddress();
            assertEquals(expectedMacAddress, effectiveMacAddress);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void testSetFeatures()
    {
        String xmlDescription = "<domain></domain>";
        String newXmlDescription = parser_.setFeatures(xmlDescription);
        assertTrue(newXmlDescription.contains("acpi"));
        assertTrue(newXmlDescription.contains("pae"));
        assertTrue(newXmlDescription.contains("apic"));
        
    }
    
    
    

}
