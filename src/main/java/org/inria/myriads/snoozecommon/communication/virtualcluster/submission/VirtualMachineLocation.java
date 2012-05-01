/**
 * Copyright (C) 2010-2012 Eugen Feller, INRIA <eugen.feller@inria.fr>
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
package org.inria.myriads.snoozecommon.communication.virtualcluster.submission;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.globals.Globals;

/**
 * Virtual machine request.
 * 
 * @author Eugen Feller
 */
public final class VirtualMachineLocation 
    implements Serializable
{
    /** Default version. */
    private static final long serialVersionUID = 1L;

    /** Virtual machine identifier. */
    private String virtualMachineId_;
    
    /** Virtual machine local controller identifier. */
    private String localControllerId_;
    
    /** Local controller address. */
    private NetworkAddress localControllerControlDataAddress_;
    
    /** Constructor. */
    public VirtualMachineLocation() 
    { 
        virtualMachineId_ = Globals.DEFAULT_INITIALIZATION;
        localControllerId_ = Globals.DEFAULT_INITIALIZATION;
        localControllerControlDataAddress_ = new NetworkAddress();
    }
    
    /** 
     * Copy constructor.
     * 
     * @param virtualMachineLocation    The virtual machine location
     */
    public VirtualMachineLocation(VirtualMachineLocation virtualMachineLocation) 
    {
        virtualMachineId_ = virtualMachineLocation.getVirtualMachineId();
        localControllerId_ = virtualMachineLocation.getLocalControllerId();
        localControllerControlDataAddress_ = virtualMachineLocation.getLocalControllerControlDataAddress();
    }
    
    /**
     * Sets the virtual machine identifier.
     * 
     * @param virtualMachineId  The virtual machine identifier
     */
    public void setVirtualMachineId(String virtualMachineId) 
    {
        virtualMachineId_ = virtualMachineId;
    }
    
    /**
     * Returns the virtual machine identifier.
     * 
     * @return  The virtual machine identifier
     */
    public String getVirtualMachineId() 
    {
        return virtualMachineId_;
    }
    
    /**
     * Sets the local controller identifier.
     * 
     * @param localControllerId     The local controller identifier
     */
    public void setLocalControllerId(String localControllerId) 
    {
        localControllerId_ = localControllerId;
    }
    
    /**
     * Returns the local controller identifier.
     * 
     * @return  The local controller identifier
     */
    public String getLocalControllerId() 
    {
        return localControllerId_;
    }

    /**
     * Sets the local controller control data address.
     * 
     * @param localControllerControlDataAddress     The local controller control data address
     */
    public void setLocalControllerControlDataAddress(NetworkAddress localControllerControlDataAddress)
    {
        localControllerControlDataAddress_ = localControllerControlDataAddress;
    }

    /**
     * Returns the local controller control data address.
     * 
     * @return      The local controller control data address
     */
    public NetworkAddress getLocalControllerControlDataAddress() 
    {
        return localControllerControlDataAddress_;
    }
}
