package org.inria.myriads.snoozecommon.parser.api.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.inria.myriads.libvirt.domain.LibvirtConfigDisk;
import org.inria.myriads.libvirt.domain.LibvirtConfigDomain;
import org.inria.myriads.libvirt.domain.LibvirtConfigFeatures;
import org.inria.myriads.libvirt.domain.LibvirtConfigGraphics;
import org.inria.myriads.libvirt.domain.LibvirtConfigInterface;
import org.inria.myriads.libvirt.domain.LibvirtConfigSerialConsole;
import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor.HypervisorDriver;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring.NetworkDemand;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineTemplate;
import org.inria.myriads.snoozecommon.communication.virtualmachine.ResizeRequest;
import org.inria.myriads.snoozecommon.exception.VirtualClusterParserException;
import org.inria.myriads.snoozecommon.globals.Globals;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.inria.myriads.snoozecommon.parser.api.VirtualClusterParser;
import org.inria.myriads.snoozecommon.parser.util.VirtualClusterParserUtils;
import org.inria.myriads.snoozecommon.util.MathUtils;
import org.inria.myriads.snoozecommon.virtualmachineimage.VirtualMachineImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LibvirtConfigParser implements VirtualClusterParser 
{

    
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(LibvirtConfigParser.class);
    
    /** Context.*/
    private JAXBContext context_ ;

    /**
     * Constructor. 
     * @throws JAXBException 
     */
    public LibvirtConfigParser() 
    {
        try
        {
            context_ = JAXBContext.newInstance(LibvirtConfigDomain.class);
        }
        catch(JAXBException e)
        {
            log_.error("Unable to initialize the parser.");
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getNetworkInterfaces(String xmlDesc) throws VirtualClusterParserException 
    {
        Guard.check(xmlDesc);
        
        
        List<String> networkInterfaces = new ArrayList<String>();
        try 
        {  
            LibvirtConfigDomain domain = unmarshal(xmlDesc);
            ArrayList<LibvirtConfigInterface> interfaces = domain.getDevices().getInterfaces();
            for (LibvirtConfigInterface interf : interfaces)
            {
                networkInterfaces.add(interf.getTargetDev());
            }
            return networkInterfaces;
        }
        catch (Exception exception)
        { 
            throw new VirtualClusterParserException(String.format("Unable to get network interface for XML : %s",
                exception.getMessage()));
        }
    }

    @Override
    public String getMacAddress(String xmlDescription) 
    {   
        try
        {
            
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            return domain.getDevices().getInterfaces().get(0).getMacAddress();
        }
        catch (JAXBException exception)
        {
            log_.error("unable to get the mac address for the template");
            return null;
        }
        
        
      
    }

    private LibvirtConfigDomain unmarshal(String xmlDescription) throws JAXBException 
    {
      //unmarshal the xml
        Unmarshaller jaxbUnmarshaller = context_.createUnmarshaller();
        InputStream input = new ByteArrayInputStream(xmlDescription.getBytes());
        LibvirtConfigDomain domain = (LibvirtConfigDomain) jaxbUnmarshaller.unmarshal(input);
        return domain;
    }

    @Override
    public String replaceMacAddressInTemplate(String xmlDesc, String newMacAddress)
    {

        try
        {
            LibvirtConfigDomain domain = unmarshal(xmlDesc);
            //make the change
            LibvirtConfigInterface inter = new LibvirtConfigInterface();
            inter.setMacAddress(newMacAddress)
                .setSourceBridge("virbr0")
                .setType("bridge");
            ArrayList<LibvirtConfigInterface> interfaces = new ArrayList<LibvirtConfigInterface>();
            interfaces.add(inter);
            domain.getDevices().setInterfaces(interfaces);
            
            //marshal it again
            String xml = marshal(domain);

            return xml;
        }
        catch (Exception e)
        {
            log_.error("Unable to add mac adress in template");
            e.printStackTrace();
            return xmlDesc;
        }

        
        //marshal it again
    }

    private String marshal(LibvirtConfigDomain domain) throws JAXBException 
    {
        Marshaller jaxbMarshaller = context_.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(domain, stream);
        return stream.toString();
    }

    @Override
    public String handleResizeRequest(String xmlDesc, ResizeRequest resizeRequest) 
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VirtualMachineMetaData parseDescription(VirtualMachineTemplate virtualMachineDescription) 
            throws Exception 
    {
        log_.debug("Parsing template description");
        VirtualMachineMetaData metaData = new VirtualMachineMetaData();
        
        String libvirtTemplate = virtualMachineDescription.getLibVirtTemplate() ;
        String name = Globals.DEFAULT_INITIALIZATION;
        int vcpus = Globals.DEFAULT_VCPU_INITIALIZATION;
        long memory = Globals.DEFAULT_MEMORY_INITIALIZATION;
        NetworkDemand networkDemand = new NetworkDemand();
        
        if (!StringUtils.isBlank(libvirtTemplate))
        {
            log_.debug("Generating description from a raw template");
            metaData.setXmlRepresentation(libvirtTemplate);
            try
            {
                LibvirtConfigDomain domain = unmarshal(libvirtTemplate);
                name = domain.getName();
                vcpus = domain.getVcpu();
                memory = domain.getMemory();
                networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
            }
            catch (JAXBException exception)
            {
                log_.debug("Unable to parse to xml string with jaxb ... falling back to legacy parser");
            }
            
        }
        else
        {
            log_.debug("Generating description from a set of arguments");
            name  = virtualMachineDescription.getName();
            vcpus = virtualMachineDescription.getVcpus();
            memory = virtualMachineDescription.getMemory();
            networkDemand = virtualMachineDescription.getNetworkCapacityDemand();
            
            LibvirtConfigDomain domain = new LibvirtConfigDomain();
            domain.setName(name)
                .setVcpu(vcpus)
                .setMemory(memory);
            
            libvirtTemplate = marshal(domain);
            
        }
        
        metaData.setXmlRepresentation(libvirtTemplate);
        metaData.getVirtualMachineLocation().setVirtualMachineId(name);
        ArrayList<Double> resourceRequirements = 
                MathUtils.createCustomVector(vcpus, memory, networkDemand); 
        metaData.setRequestedCapacity(resourceRequirements);
        
        return metaData;
    }

    @Override 
    public String addCdRomImage(String xmlDesc, String path, String bus, String dev)
    {
        log_.debug("Adding cd rom to the template");
        try
        {
            LibvirtConfigDomain domain = unmarshal(xmlDesc);
            LibvirtConfigDisk disk = new LibvirtConfigDisk();
            ArrayList<LibvirtConfigDisk> disks = domain.getDevices().getDisks();
            disk.setType("file")
                .setDevice("cdrom")
                .setSourceFile(path)
                .setTargetBus(bus)
                .setTargetDev(dev);
            disks.add(disk);
            String xml = marshal(domain);    
            return xml;
        }
        catch (Exception exception)
        {
            log_.error("Unable to add this disk image");
            exception.printStackTrace();
        }
        
        return xmlDesc;
    }
    @Override
    public String addDiskImage(String xmlDesc, VirtualMachineImage image, String bus, String dev, String cache) 
    {
        try 
        {
            log_.debug("Adding disks to the template");
            log_.debug(String.format(
                    "disk : %s" +
                    "disk : %s" +
                    "format : %s", 
                    image.getName(),
                    image.getPath(),
                    image.getFormat()));
            
            LibvirtConfigDomain domain = unmarshal(xmlDesc);
            LibvirtConfigDisk disk = new LibvirtConfigDisk();
            ArrayList<LibvirtConfigDisk> disks = domain.getDevices().getDisks();
            disk.setSourceFile(image.getPath());
            if (image.getFormat().equals("raw"))
            {
                disk.setDriverName("qemu");
                disk.setDriverType("raw");
            }
            else if (image.getFormat().equals("qcow2"))
            {
                disk.setDriverName("qemu");
                disk.setDriverType("qcow2");
            }
            else
            {
                throw new
                JAXBException(String.format("the file format %s isn't implemented in the parser", image.getFormat()));
            }
            disk.setTargetBus(bus)
                .setTargetDev(dev)
                .setDriverCache(cache);
            
            disks.add(disk);
            String xml = marshal(domain);
            return xml;
        } 
        catch (JAXBException e) 
        {
            log_.error("Unable to add this disk image");
            e.printStackTrace();
        }
        return xmlDesc;
    }

    private String generateTargetDev(int size) 
    {
        char c = (char) (size+97);
        return "vd"+c;
    }

    @Override
    public String addSerial(String xmlDescription, String type, String targetPort) 
    {
        String newXmlDescription = xmlDescription;
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            LibvirtConfigSerialConsole serial = new LibvirtConfigSerialConsole();
            serial.setType(type)
                .setTargetPort(targetPort);
            domain.addConsole(serial);
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable to add a console");
            e.printStackTrace();
        }
        return newXmlDescription;            
    }
    
    @Override
    public String addSerial(String xmlDescription)
    {
        String newXmlDescription = xmlDescription;
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            LibvirtConfigSerialConsole serial = new LibvirtConfigSerialConsole();
            domain.addConsole(serial);
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable to add a console");
            e.printStackTrace();
        }
        return newXmlDescription;
    }


    @Override
    public String addConsole(
            String xmlDescription,
            String type,
            String targetPort,
            String targetType) 
    {
        String newXmlDescription = xmlDescription;
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            LibvirtConfigSerialConsole console = new LibvirtConfigSerialConsole();
            console
                .setType(type)
                .setTargetPort(targetPort)
                .setTargetType(targetType);
            domain.addConsole(console);
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable to add a console");
            e.printStackTrace();
        }
        return newXmlDescription;
    }

    @Override
    public String setDomainType(String xmlDescription, HypervisorDriver driver) 
    {
        String newXmlDescription = xmlDescription;
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            switch(driver)
            {
            case qemu :
                domain.setType("kvm");
                break;
            case xen :
                domain.setType("xen");
                break;
            case test :
                domain.setType("test");
                break;
            default:
                domain.setType("test");
                break;
            }
            
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable to add a console");
            e.printStackTrace();
        }
        return newXmlDescription;
    }

    @Override
    public String setOsType(String xmlDescription, HypervisorDriver driver) 
    {
        String newXmlDescription = xmlDescription;
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            //hard coded.
            domain.setOsType("hvm");
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable to add a console");
            e.printStackTrace();
        }
        return newXmlDescription;
    }

    @Override
    public VirtualMachineImage getFirstDiskImage(String xmlDescription) 
    {
        try {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            LibvirtConfigDisk disk = domain.getDevices().getDisks().get(0);
            VirtualMachineImage image = new VirtualMachineImage();
            image.setPath(disk.getSourceFile());
            image.setName(FilenameUtils.getName(disk.getSourceFile()));
            return image;
        }
        catch (Exception e)
        {
            log_.error("Unable to get the first disk image");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String removeDisk(String xmlDescription, String name) {
        String newXmlDescription = xmlDescription;
        try 
        {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            ArrayList<LibvirtConfigDisk> disks = domain.getDevices().getDisks();
            Iterator<LibvirtConfigDisk> i = disks.iterator();
            while (i.hasNext()) 
            {
               LibvirtConfigDisk disk = i.next();
               if (disk.getSourceFile().contains(name)){
                   i.remove();    
               }
            }
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e) 
        {   
            log_.error("Unable remove the disk");
            e.printStackTrace();
        }
        return newXmlDescription;
    }

    
    @Override
    public String addGraphics(String xmlDescription, String type, String address, String port, String keymap)
    {
        String newXmlDescription = xmlDescription;
        try 
        {
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            LibvirtConfigGraphics graphics = new LibvirtConfigGraphics();
            graphics.setType(type)
            .setListen(address)
            .setPort(port)
            .setKeymap(keymap);
            domain.addGraphics(graphics);
            
            newXmlDescription = marshal(domain);
        }
        catch (JAXBException e)
        {
            log_.error("Unable to add graphics");
            e.printStackTrace();
        }
        return newXmlDescription;
    }

    @Override
    public String setFeatures(String xmlDescription)
    {
        String newXmlDescription = xmlDescription;
        try{
            LibvirtConfigDomain domain = unmarshal(xmlDescription);
            
            LibvirtConfigFeatures features = new LibvirtConfigFeatures();
            features.enableAcpi();
            features.enableApic();
            features.enablePae();
            domain.setFeatures(features);
            
            newXmlDescription = marshal(domain);
        }
        catch(JAXBException e)
        {
            log_.error("Unable to set the features");
            log_.error(e.getMessage());
        }
        return newXmlDescription;
    }
    
}
