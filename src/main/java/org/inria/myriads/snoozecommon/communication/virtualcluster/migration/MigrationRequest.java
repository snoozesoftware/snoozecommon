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
package org.inria.myriads.snoozecommon.communication.virtualcluster.migration;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor.HypervisorSettings;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * Local controller migration request message.
 * 
 * @author Eugen Feller
 */
public final class MigrationRequest 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Source virtual machine location. */
    private VirtualMachineLocation sourceVirtualMachineLocation_;
    
    /** Destination virtual machine location. */
    private VirtualMachineLocation destinationVirtualMachineLocation_;
    
    /** Destination hypervisor settings. */
    private HypervisorSettings destinationHypervisorSettings_;
    
    /** Indicates migration success. */
    private boolean isMigrated_;
    
    /**
     * Sets the source virtual machine location.
     * 
     * @param sourceVirtualMachineLocation  The source virtual machine location
     */
    public void setSourceVirtualMachineLocation(VirtualMachineLocation sourceVirtualMachineLocation) 
    {
        sourceVirtualMachineLocation_ = sourceVirtualMachineLocation;
    }
    
    /**
     * Returns the source virtual machine location.
     * 
     * @return  The source virtual machine location
     */
    public VirtualMachineLocation getSourceVirtualMachineLocation() 
    {
        return sourceVirtualMachineLocation_;
    }

    /**
     * Sets the destination hypervisor port.
     * 
     * @param hypervisorSettings     The destination hypervisor settings
     */
    public void setDestinationHypervisorSettings(HypervisorSettings hypervisorSettings) 
    {
        destinationHypervisorSettings_ = hypervisorSettings;
    }

    /**
     * Returns the destination hypervisort port.
     * 
     * @return  The destination hypervisor port
     */
    public HypervisorSettings getDestinationHypervisorSettings() 
    {
        return destinationHypervisorSettings_;
    }

    /**
     * Sets the destination virtual machine location.
     * 
     * @param destinationVirtualMachineLocation     The destination virtual machine location
     */
    public void setDestinationVirtualMachineLocation(VirtualMachineLocation destinationVirtualMachineLocation) 
    {
        destinationVirtualMachineLocation_ = destinationVirtualMachineLocation;
    }

    /**
     * Returns the destination virtual machine location.
     * 
     * @return  The destination  virtual machine location
     */
    public VirtualMachineLocation getDestinationVirtualMachineLocation() 
    {
        return destinationVirtualMachineLocation_;
    }

    /**
     * Sets is migrated flag.
     * 
     * @param isMigrated       The flag
     */
    public void setMigrated(boolean isMigrated) 
    {
        isMigrated_ = isMigrated;
    }

    /**
     * Returns is migrated flag.
     * 
     * @return      true if migrated, false otherwise
     */
    public boolean isMigrated() 
    {
        return isMigrated_;
    }
}
