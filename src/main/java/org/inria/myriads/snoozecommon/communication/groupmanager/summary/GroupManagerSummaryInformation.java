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
package org.inria.myriads.snoozecommon.communication.groupmanager.summary;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.inria.myriads.snoozecommon.guard.Guard;

/**
 * Group manager resource information.
 * 
 * @author Eugen Feller
 */
public final class GroupManagerSummaryInformation 
    implements Serializable 
{
    /** Default version. */
    private static final long serialVersionUID = 1L;

    /** The time stamp. */
    private long timeStamp_;
    
    /** 
     *  Total active resource capacity.
     *  
     *  Format: CPU utilization, Memory utilization, Network Rx traffic, Network Tx traffic
     */
    private ArrayList<Double> activeCapacity_;
    
    /** 
     *  Total passive resource capacity.
     *  
     *  Format: CPU utilization, Memory utilization, Network Rx traffic, Network Tx traffic
     */
    private ArrayList<Double> passiveCapacity_;
    
    /** 
     * Requested resource capacity.
     * 
     * Format: CPU utilization, Memory utilization, Network Rx traffic, Network Tx traffic
     */
    private ArrayList<Double> requestedCapacity_;
    
    /** 
     * Used resource capacity.
     * 
     * Format: CPU utilization, Memory utilization, Network Rx traffic, Network Tx traffic
     */
    private ArrayList<Double> usedCapacity_;
               
    /**
     * List of legacy IP addresses.
     */
    private ArrayList<String> legacyIpAddresses_;
    
    /** Empty constructor. */
    public GroupManagerSummaryInformation() 
    {
        setTimeStamp(new Timestamp(System.currentTimeMillis()).getTime()); 
    }
    
    /**
     * Copy constructor.
     * 
     * @param summary       The original object
     */
    public GroupManagerSummaryInformation(GroupManagerSummaryInformation summary) 
    {
        Guard.check(summary);
        timeStamp_ = summary.getTimeStamp().longValue();
        usedCapacity_ = new ArrayList<Double>(summary.getUsedCapacity());
        requestedCapacity_ = new ArrayList<Double>(summary.getRequestedCapacity());
        activeCapacity_ = new ArrayList<Double>(summary.getActiveCapacity());
        passiveCapacity_ = new ArrayList<Double>(summary.getPassiveCapacity());
        legacyIpAddresses_ = new ArrayList<String>(summary.getLegacyIpAddresses());
    }

    /**
     * Sets the used capacity.
     * 
     * @param usedCapacity    The used capacity
     */
    public void setUsedCapacity(ArrayList<Double> usedCapacity) 
    {
        usedCapacity_ = usedCapacity;
    }
    
    /**
     * Returns the used capacity.
     * 
     * @return     The used capacity
     */
    public ArrayList<Double> getUsedCapacity() 
    {
        return usedCapacity_;
    }
    
    /**
     * Sets the active capacity.
     * 
     * @param activeCapacity    The active capacity
     */
    public void setActiveCapacity(ArrayList<Double> activeCapacity) 
    {
        activeCapacity_ = activeCapacity;
    }
    
    /**
     * Returns the active capacity.
     * 
     * @return  The active capacity capacity
     */
    public ArrayList<Double> getActiveCapacity() 
    {
        return activeCapacity_;
    }
    
    /**
     * Sets the passive capacity.
     * 
     * @param passiveCapacity the passiveCapacity to set
     */
    public void setPassiveCapacity(ArrayList<Double> passiveCapacity) 
    {
        passiveCapacity_ = passiveCapacity;
    }
    
    /**
     * Returns the passive capacity.
     * 
     * @return  The passive capacity
     */
    public ArrayList<Double> getPassiveCapacity() 
    {
        return passiveCapacity_;
    }
    
    /**
     * Sets the legacy addresses.
     * 
     * @param legacyIpAddresses     The legacy addresses
     */
    public void setLegacyIpAddresses(ArrayList<String> legacyIpAddresses) 
    {
        legacyIpAddresses_ = legacyIpAddresses;
    }
    
    /**
     * Returns the legacy IP addresses.
     * 
     * @return  The list of addresses
     */
    public ArrayList<String> getLegacyIpAddresses()
    {
        return legacyIpAddresses_;
    }

    /**
     * Sets the requested capacity.
     * 
     * @param requestedCapacity     The requested capacity
     */
    public void setRequestedCapacity(ArrayList<Double> requestedCapacity) 
    {
        requestedCapacity_ = requestedCapacity;
    }

    /**
     * Returns the requested capacity.
     * 
     * @return  The requested capacity
     */
    public ArrayList<Double> getRequestedCapacity() 
    {
        return requestedCapacity_;
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
