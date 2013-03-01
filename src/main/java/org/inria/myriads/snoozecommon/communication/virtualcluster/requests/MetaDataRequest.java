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
package org.inria.myriads.snoozecommon.communication.virtualcluster.requests;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * Meta data request.
 * 
 * @author Eugen Feller
 */
public final class MetaDataRequest 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Virtual machine location. */
    private VirtualMachineLocation virtualMachineLocation_;
    
    /** Number of monitoring entries. */
    private int numberOfMonitoringEntries_;
    
    /** Empty constructor. */
    public MetaDataRequest()
    { 
    } 
    
    /**
     * Sets the virtual machine location.
     * 
     * @param virtualMachineLocation    The virtual machine location
     */
    public void setVirtualMachineLocation(VirtualMachineLocation virtualMachineLocation) 
    {
        virtualMachineLocation_ = virtualMachineLocation;
    }
    
    /**
     * Returns the virtual machine location.
     * 
     * @return  The virtual machine location
     */
    public VirtualMachineLocation getVirtualMachineLocation() 
    {
        return virtualMachineLocation_;
    }
    
    /**
     * Sets the number of monitoring entries.
     * 
     * @param numberOfMonitoringEntries     The number of monitoring entries
     */
    public void setNumberOfMonitoringEntries(int numberOfMonitoringEntries) 
    {
        numberOfMonitoringEntries_ = numberOfMonitoringEntries;
    }
    
    /**
     * Returns the number of monitoring entries.
     * 
     * @return  The number of monitoring entries
     */
    public int getNumberOfMonitoringEntries() 
    {
        return numberOfMonitoringEntries_;
    }
}
