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
import java.util.ArrayList;

import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;

/**
 * Virtual machine submission request.
 * 
 * @author Eugen Feller
 */
public class VirtualMachineSubmissionRequest 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Virtual machine meta data. */
    private ArrayList<VirtualMachineMetaData> virtualMachineMetaData_;
    
    /**
     * Sets the virtual machine meta data.
     * 
     * @param virtualMachineMetaData  The meta data data
     */
    public final void setVirtualMachineMetaData(ArrayList<VirtualMachineMetaData> virtualMachineMetaData) 
    {
        virtualMachineMetaData_ = virtualMachineMetaData;
    }
    
    /**
     * Returns the virtual machine meta data.
     * 
     * @return  The meta data
     */
    public final ArrayList<VirtualMachineMetaData> getVirtualMachineMetaData() 
    {
        return virtualMachineMetaData_;
    }
    
    
}
