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
package org.inria.myriads.snoozecommon.communication.virtualcluster.submission;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring.NetworkDemand;
import org.inria.myriads.snoozecommon.globals.Globals;

/**
 * Virtual machine description.
 * 
 * @author Eugen Feller
 */
public final class VirtualMachineTemplate 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Network capacity. */
    private NetworkDemand networkCapacityDemand_;
    
    /** Libvirt description. */
    private String libVirtDescription_;
    
    /** Vcpu request.*/
    private int vcpus_;
    
    /** memory request.*/
    private long memory_;
    
    /** image id.*/
    private String imageId_;
    
    /** virtual machine name.*/
    private String name_;
    
    /** Node{group manager id or local controller id} Id (force placement). */
    private String hostId_;
    
    /** Constructor. */
    public VirtualMachineTemplate()
    {
        networkCapacityDemand_ = new NetworkDemand();
        hostId_ = Globals.DEFAULT_INITIALIZATION;
        vcpus_ = Globals.DEFAULT_VCPU_INITIALIZATION;
        memory_ = Globals.DEFAULT_MEMORY_INITIALIZATION;
    }
    
    /**
     * Sets the network capacity.
     * 
     * @param networkCapacity   The network capacity
     */
    public void setNetworkCapacityDemand(NetworkDemand networkCapacity) 
    {
        networkCapacityDemand_ = networkCapacity;
    }
    
    /**
     * Returns the network capacity.
     * 
     * @return  The network capacity
     */
    public NetworkDemand getNetworkCapacityDemand() 
    {
        return networkCapacityDemand_;
    }

    /**
     * Sets the libvirt description.
     * 
     * @param libVirtDescription    The libvirt template
     */
    public void setLibVirtTemplate(String libVirtDescription) 
    {
        libVirtDescription_ = libVirtDescription;
    }

    /**
     * Returns the libvirt description.
     * 
     * @return      The libvirt template
     */
    public String getLibVirtTemplate() 
    {
        return libVirtDescription_;
    }

    /**
     * @return the hostId
     */
    public String getHostId() 
    {
        return hostId_;
    }

    /**
     * @param hostId the hostId to set
     */
    public void setHostId(String hostId) 
    {
        hostId_ = hostId;
    }

    /**
     * @return the vcpus
     */
    public int getVcpus() 
    {
        return vcpus_;
    }

    /**
     * @param vcpus the vcpus to set
     */
    public void setVcpus(int vcpus) 
    {
        vcpus_ = vcpus;
    }

    /**
     * @return the memory
     */
    public long getMemory() 
    {
        return memory_;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(long memory) 
    {
        memory_ = memory;
    }

    /**
     * @return the imageId
     */
    public String getImageId() 
    {
        return imageId_;
    }

    /**
     * @param imageId the imageId to set
     */
    public void setImageId(String imageId) 
    {
        imageId_ = imageId;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name_;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        name_ = name;
    }

    /**
     * @return the libVirtDescription
     */
    public String getLibVirtDescription() 
    {
        return libVirtDescription_;
    }

    /**
     * @param libVirtDescription the libVirtDescription to set
     */
    public void setLibVirtDescription(String libVirtDescription) 
    {
        libVirtDescription_ = libVirtDescription;
    }
}
