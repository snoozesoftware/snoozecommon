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
package org.inria.myriads.snoozecommon.communication.virtualmachine;

import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * 
 * Client Migration Request.
 * 
 * @author msimonin
 *
 */
public class ClientMigrationRequest 
{

    /** The old loclation.*/
    private VirtualMachineLocation oldLocation_;
    
    /** The new loclation.*/
    private VirtualMachineLocation newLocation_;

    /**
     * 
     * Empty Constructor.
     * 
     */
    public ClientMigrationRequest() 
    {
        
    }
    
    
    /**
     * 
     * Constructor.
     * 
     * @param oldLocation           The old location 
     * @param newLocation           The new location
     */
    public ClientMigrationRequest(VirtualMachineLocation oldLocation,
            VirtualMachineLocation newLocation) 
    {
        oldLocation_ = oldLocation;
        newLocation_ = newLocation;
    }


    /**
     * @return the oldLocation
     */
    public VirtualMachineLocation getOldLocation() 
    {
        return oldLocation_;
    }


    /**
     * @param oldLocation the oldLocation to set
     */
    public void setOldLocation(VirtualMachineLocation oldLocation) 
    {
        oldLocation_ = oldLocation;
    }


    /**
     * @return the newLocation
     */
    public VirtualMachineLocation getNewLocation() 
    {
        return newLocation_;
    }


    /**
     * @param newLocation the newLocation to set
     */
    public void setNewLocation(VirtualMachineLocation newLocation)
    {
        newLocation_ = newLocation;
    }
    
    
    
}
