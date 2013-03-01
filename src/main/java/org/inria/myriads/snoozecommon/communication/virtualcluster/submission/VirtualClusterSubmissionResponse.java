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

import org.inria.myriads.snoozecommon.communication.virtualcluster.status.VirtualClusterErrorCode;

/**
 * Virtual cluster response.
 * 
 * @author Eugen Feller
 */
public final class VirtualClusterSubmissionResponse extends VirtualMachineSubmissionResponse
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Error code. */
    private VirtualClusterErrorCode errorCode_;
    
    /**
     * Sets the error code.
     * 
     * @param errorCode     The error code
     */
    public void setErrorCode(VirtualClusterErrorCode errorCode) 
    {
        errorCode_ = errorCode;
    }
    
    /**
     * Returns the error code.
     * 
     * @return  The error code
     */
    public VirtualClusterErrorCode getErrorCode()
    {
        return errorCode_;
    }
}
