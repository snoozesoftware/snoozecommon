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
package org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring;

import java.io.Serializable;

/**
 * Network demand.
 * 
 * @author Eugen Feller
 */
public final class NetworkDemand 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;

    /** Rx byes. */
    private double rxBytes_;
    
    /** Tx byes. */
    private double txBytes_;
        
    /** Empty constructor. */
    public NetworkDemand()
    {
        rxBytes_ = 0;
        txBytes_ = 0;
    }

    /** 
     * Constructor.
     * 
     * @param rxBytes   The number of rxbytes
     * @param txBytes   The number of txBytes
     */
    public NetworkDemand(double rxBytes, double txBytes)
    {
        rxBytes_ = rxBytes;
        txBytes_ = txBytes;
    }
    
    /**
     * Sets the Rx bytes.
     * 
     * @param rxBytes   The Rx bytes
     */
    public void setRxBytes(double rxBytes)
    {
        rxBytes_ = rxBytes;
    }
    
    /**
     * Sets the Tx bytes.
     * 
     * @param txBytes   The Tx bytes
     */
    public void setTxBytes(double txBytes)
    {
        txBytes_ = txBytes;
    }
    
    /**
     * Returns the Rx bytes.
     * 
     * @return  The Rx byres
     */
    public double getRxBytes() 
    {
        return rxBytes_;
    }
    
    /**
     * Returns the Tx bytes.
     * 
     * @return  The Tx byres
     */
    public double getTxBytes() 
    {
        return txBytes_;
    }
}
