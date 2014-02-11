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
package org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.inria.myriads.snoozecommon.guard.Guard;

/**
 * Virtual machine monitoring data.
 * 
 * @author Eugen Feller
 */
public final class HostMonitoringData 
    implements Serializable 
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
        
    /** The time stamp. */
    private long timeStamp_;
    
    /** 
     * Used capacity. 
     * metric -> value
     *  
     */
    private HashMap<String, Double> usedCapacity_;
    
    /** Empty constructor. */
    public HostMonitoringData() 
    {   
        timeStamp_ = new Timestamp(System.currentTimeMillis()).getTime(); 
        usedCapacity_ = new HashMap<String, Double>();
    }
    
    /**
     * Copy constructor.
     * 
     * @param originalData  The virtual machine monitoring data
     */
    public HostMonitoringData(HostMonitoringData originalData)
    {
        Guard.check(originalData);
        timeStamp_ = originalData.getTimeStamp();
        usedCapacity_ = new HashMap<String, Double>(originalData.getUsedCapacity());
    }
    
    /**
     * Sets the used capacity.
     * 
     * @param usedCapacity The used capacity
     */
    public void setUsedCapacity(HashMap<String, Double> usedCapacity)
    {
        usedCapacity_ = usedCapacity;
    }
    
    /**
     * Returns the used capacity.
     * 
     * @return The used capacity
     */
    public HashMap<String, Double> getUsedCapacity()
    {
        return usedCapacity_;
    }

    /**
     * Sets the time stamp.
     * 
     * @param timeStamp  The time stamp
     */
    public void setTimeStamp(long timeStamp) 
    {
        timeStamp_ = timeStamp;
    }
    
    /**
     * Returns the time stamp.
     * 
     * @return  The time stamp
     */
    public Long getTimeStamp() 
    {
        return new Long(timeStamp_);
    }
}
