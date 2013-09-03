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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * 
 * Resize Request.
 * 
 * @author msimonin
 *
 */
public class ResizeRequest implements Serializable 
{
     /** Serial Id. */
    private static final long serialVersionUID = 1L;
    
    /** Virtual Machine Id.*/
    private VirtualMachineLocation virtualMachineLocation_;
    
    /** the resized capacity.*/
    private ArrayList<Double> resizedCapacity_;

    /**
     * Constructor.
     */
    public ResizeRequest() 
    {
        virtualMachineLocation_ = new VirtualMachineLocation();
        resizedCapacity_ = new ArrayList<Double>(Arrays.asList(-1d, -1d, -1d, -1d));
    }

    /**
     * Constructor.
     * 
     * @param resizeRequest         The resize request.
     * 
     */
    public ResizeRequest(ResizeRequest resizeRequest)
    {
        virtualMachineLocation_ = resizeRequest.getVirtualMachineLocation();
        resizedCapacity_ = resizeRequest.getResizedCapacity();
    }
    
    /**
     * @return the resizedCapacity
     */
    public ArrayList<Double> getResizedCapacity() 
    {
        return resizedCapacity_;
    }

    /**
     * @param resizedCapacity the resizedCapacity to set
     */
    public void setResizedCapacity(ArrayList<Double> resizedCapacity) 
    {
        resizedCapacity_ = resizedCapacity;
    }

    /**
     * @return the virtualMachineLocation
     */
    public VirtualMachineLocation getVirtualMachineLocation() 
    {
        return virtualMachineLocation_;
    }

    /**
     * @param virtualMachineLocation the virtualMachineLocation to set
     */
    public void setVirtualMachineLocation(
            VirtualMachineLocation virtualMachineLocation)
    {
        virtualMachineLocation_ = virtualMachineLocation;
    }
}
