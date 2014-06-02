/**
 * Copyright (C) 2010-2013 Eugen Feller, INRIA <eugen.feller@inria.fr>
 *
 * This file is part of Snooze, a scalable, autonomic, and
 * energy-aware virtual machine (VM) management framework.
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package org.inria.myriads.snoozecommon.parser.api;

import java.util.ArrayList;
import java.util.List;

import org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor.HypervisorDriver;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineTemplate;
import org.inria.myriads.snoozecommon.communication.virtualmachine.ResizeRequest;
import org.inria.myriads.snoozecommon.exception.VirtualClusterParserException;
import org.inria.myriads.snoozecommon.virtualmachineimage.VirtualMachineImage;


/**
 * Virtual cluster parster interface.
 * 
 * @author Eugen Feller
  */
public interface VirtualClusterParser 
{
    
    /**
     * Get the network interfaces for the a xml description.
     * 
     * @param xmlDescription                    the xml description    
     * @return                                  List of network interfaces
     * @throws VirtualClusterParserException    Exception
     */
    List<String> getNetworkInterfaces(String xmlDescription) throws VirtualClusterParserException;
    
    
    /**
     *  Gets the MAC from template.
     * 
     * @param xmlDescription        xml template
     * @return                      mac address
     */
    String getMacAddress(String xmlDescription);
    
    /**
     *  Replace the MAC from template.
     * 
     * @param xmlDesc           template
     * @param newMacAddress     the new address
     * @return                  mac address
     */
    String replaceMacAddressInTemplate(String xmlDesc, String newMacAddress);
    
    /**
     *  Handle the Resize request.
     * 
     * @param xmlDesc           template
     * @param resizeRequest     the resize request
     * @return                  the new xml description of the domain
     */
    String handleResizeRequest(String xmlDesc, ResizeRequest resizeRequest);
    
    /**
     * Start processing the file.
     * 
     * @param virtualMachineDescription     The virtual machine description
     * @return                              The virtual machine meta data
     * @throws Exception                    Exception 
     */
    VirtualMachineMetaData parseDescription(VirtualMachineTemplate virtualMachineDescription) throws Exception;
    

    /**
     * 
     * Adds a disk image to the template.
     * 
     * @param xmlDesc   The xml description
     * @param image     The image to add
     * @param dev       dev settings
     * @param bus       bus settings
     * @param cache     cache settings 
     * @return          The new template.
     */
    String addDiskImage(String xmlDesc, VirtualMachineImage image, String bus, String dev, String cache);

    
    /**
     * 
     * Adds a serial.
     * 
     * @param xml           the xml Desc.
     * @param type          type of serial.
     * @param targetPort    target port.
     * @return the xml description
     */
    String addSerial(String xml, String type, String targetPort);


    /**
     * 
     * Adds a console
     * 
     * @param xmlDescription    the xml description.
     * @param type              the type
     * @param targetPort        the target port
     * @param targetType        the target type
     * @return the xml description
     */
    String addConsole(
            String xmlDescription, 
            String type, 
            String targetPort,
            String targetType);


    /**
     * 
     * sets the driver type.
     * 
     * @param xmlDescription    the xml description
     * @param driver            the driver
     * @return the xml description
     */
    String setDomainType(String xmlDescription, HypervisorDriver driver);


    /**
     * 
     * sets the os type.
     * 
     * @param xmlDescription    the xml description
     * @param driver            the driver type
     * @return the xml description
     */
    String setOsType(String xmlDescription, HypervisorDriver driver);


    /**
     * 
     * Add a cd rom image.
     * 
     * @param xmlDesc      xmlDescription
     * @param path         the path
     * @param dev          dev to be attached on
     * @param bus          the bus to use
     * @return the cd rom.
     */
    String addCdRomImage(String xmlDesc, String path, String bus, String dev);


    /**
     * 
     * Gets the first disk image.
     *
     * @param xmlDescription the xml description
     * @return the disk
     */
    VirtualMachineImage getFirstDiskImage(String xmlDescription);


    
    /**
     * 
     * Remove a disk matchin the name given in parameters.
     * 
     * 
     * @param xmlRepresentation     The xml description
     * @param name                  The name of the disk to remove.
     * @return  the modified xml description.
     */
    String removeDisk(String xmlRepresentation, String name);


    /**
     * 
     * Adds a graphics.
     * 
     * @param xmlDescription    xmlDescription
     * @param type              type
     * @param address           address
     * @param port              port   
     * @param keymap            keymap
     * @return xml description  description
     */
    String addGraphics(String xmlDescription, String type, String address, String port, String keymap);
    

    /**
     * 
     * Sets all the power features.
     * 
     * @param xmlDescription xmlDescription
     * @return  xml description
     */
    String setFeatures(String xmlDescription);


    /**
     * 
     * Add a simple serial console.
     * 
     * @param xmlDescription    the xml description
     * @return xml description
     */
    String addSerial(String xmlDescription);
    
}

