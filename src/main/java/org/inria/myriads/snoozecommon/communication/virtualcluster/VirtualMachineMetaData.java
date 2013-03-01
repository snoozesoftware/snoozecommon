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
package org.inria.myriads.snoozecommon.communication.virtualcluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;
import org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring.VirtualMachineMonitoringData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.status.VirtualMachineErrorCode;
import org.inria.myriads.snoozecommon.communication.virtualcluster.status.VirtualMachineStatus;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;
import org.inria.myriads.snoozecommon.datastructure.LRUCache;
import org.inria.myriads.snoozecommon.globals.Globals;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Virtual machine meta data.
 * 
 * @author Eugen Feller
 */
public final class VirtualMachineMetaData
    implements Serializable
{
    /** Logger. */
    private static final Logger log_ = LoggerFactory.getLogger(GroupManagerDescription.class);
    
    /** Default serial. */
    private static final long serialVersionUID = 1L;
            
    /** IP address. */
    private String ipAddress_;
    
    /** XML representation. */
    private String xmlRepresentation_;
    
    /** Status. */
    private VirtualMachineStatus status_;

    /** Error code. */
    private VirtualMachineErrorCode errorCode_;
    
    /** Location. */
    private VirtualMachineLocation location_;
            
    /** Group manager address. */
    private NetworkAddress groupManagerControlDataAddress_;
    
    /** Virtual monitoring history data. */
    private LRUCache<Long, VirtualMachineMonitoringData> usedCapacity_;
        
    /** Requested capacity. */
    private ArrayList<Double> requestedCapacity_;
         
    /**
     * Constructor.
     */
    public VirtualMachineMetaData() 
    {    
        ipAddress_ = Globals.DEFAULT_INITIALIZATION;
        status_ = VirtualMachineStatus.UNKNOWN; 
        errorCode_ = VirtualMachineErrorCode.UNKNOWN;
        location_ = new VirtualMachineLocation();
        groupManagerControlDataAddress_ = new NetworkAddress();
        usedCapacity_ = new LRUCache<Long, VirtualMachineMonitoringData>();
    }
    
    /**
     * Copy constructor.
     * 
     * @param metaData                        The original meta data
     * @param numberOfMonitoringEntries       The number of monitoring entries
     */ 
    public VirtualMachineMetaData(VirtualMachineMetaData metaData, int numberOfMonitoringEntries) 
    {
        Guard.check(metaData, numberOfMonitoringEntries);
        ipAddress_ = metaData.getIpAddress();
        location_ = new VirtualMachineLocation(metaData.getVirtualMachineLocation());
        status_ = metaData.getStatus();
        errorCode_ = metaData.getErrorCode();
        groupManagerControlDataAddress_ = new NetworkAddress(metaData.getGroupManagerControlDataAddress());
        usedCapacity_ = metaData.getMonitoringData(numberOfMonitoringEntries);
        requestedCapacity_ = new ArrayList<Double>(metaData.getRequestedCapacity());
        xmlRepresentation_ = metaData.getXmlRepresentation();
    }

    /**
     * Returns the algorithm monitoring data.
     * 
     * @param numberOfMonitoringEntries   The maximum number of monitoring entries
     * @return                            The virtual machine monitoring data
     */
    private LRUCache<Long, VirtualMachineMonitoringData> getMonitoringData(int numberOfMonitoringEntries)
    {
        Guard.check(numberOfMonitoringEntries);
                
        LRUCache<Long, VirtualMachineMonitoringData> result = 
            new LRUCache<Long, VirtualMachineMonitoringData>(numberOfMonitoringEntries);   
        
        // Indeed we want the n most recent values -> Reverse the list
        List<VirtualMachineMonitoringData> reverseList = Lists.reverse(Lists.newArrayList(usedCapacity_.values()));
        for (VirtualMachineMonitoringData monitoringData : reverseList)
        {
            VirtualMachineMonitoringData copiedEntity = new VirtualMachineMonitoringData(monitoringData);
            if (result.size() == numberOfMonitoringEntries)
            {
                break;
            }
            
            log_.debug(String.format("Copied virtual machine monitoring data. Time: %s, Used capacity: %s", 
                                     copiedEntity.getTimeStamp(),
                                     copiedEntity.getUsedCapacity())); 
            result.put(copiedEntity.getTimeStamp(), copiedEntity);
        }
        
        return result;
    }
    
    /**
     * Sets the requested requirements.
     * 
     * @param requestedCapacity  The requested capacity
     */
    public void setRequestedCapacity(ArrayList<Double> requestedCapacity) 
    {
        requestedCapacity_ = requestedCapacity;
        //change in the libvirt template too in case of hard resize...
        
    }

    /**
     * Returns the requested requirements.
     * 
     * @return  The requested capacity
     */
    public ArrayList<Double> getRequestedCapacity() 
    {
        return requestedCapacity_;
    }
    
    /**
     * Sets the XML representation.
     * 
     * @param xmlRepresentation     The XML representation
     */
    public void setXmlRepresentation(String xmlRepresentation) 
    {
        xmlRepresentation_ = xmlRepresentation;
    }
    
    /**
     * Returns the XML representation.
     * 
     * @return  The XML representation
     */
    public String getXmlRepresentation() 
    {
        return xmlRepresentation_;
    }

    /**
     * Sets the used capacity.
     * 
     * @param usedCapacity    The used capacity
     */
    public void setUsedCapacity(LRUCache<Long, VirtualMachineMonitoringData> usedCapacity) 
    {
        usedCapacity_ = usedCapacity;
    }

    /**
     * Returns the used capacity.
     * 
     * @return  The used capacity
     */
    public LRUCache<Long, VirtualMachineMonitoringData> getUsedCapacity() 
    {
        return usedCapacity_;
    }

    /**
     * Sets the status.
     * 
     * @param status    The status
     */
    public void setStatus(VirtualMachineStatus status) 
    {
        status_ = status;
    }

    /**
     * Returns the status.
     * 
     * @return  The status
     */
    public VirtualMachineStatus getStatus() 
    {
        return status_;
    }

    /**
     * Sets the error code.
     * 
     * @param errorCode     The error code
     */
    public void setErrorCode(VirtualMachineErrorCode errorCode) 
    {
        errorCode_ = errorCode;
    }

    /**
     * Returns the error code.
     * 
     * @return  The error code
     */
    public VirtualMachineErrorCode getErrorCode() 
    {
        return errorCode_;
    }
    
    /**
     * Sets the virtual machine location.
     * 
     * @param location  The virtual machine location
     */
    public void setVirtualMachineLocation(VirtualMachineLocation location) 
    {
        location_ = location;
    }
    
    /**
     * Returns the virtual machine location.
     * 
     * @return  The virtual machine location
     */
    public VirtualMachineLocation getVirtualMachineLocation() 
    {
        return location_;
    }
    
    /**
     * Returns the group manager control data address.
     * 
     * @param groupManagerControlDataAddress    The control data address
     */
    public void setGroupManagerControlDataAddress(NetworkAddress groupManagerControlDataAddress) 
    {
        groupManagerControlDataAddress_ = groupManagerControlDataAddress;
    }

    /**
     * Sets the group manager control data address.
     * 
     * @return    The group manager control data address
     */
    public NetworkAddress getGroupManagerControlDataAddress() 
    {
        return groupManagerControlDataAddress_;
    }
    
    /**
     * Sets the ip address.
     * 
     * @param assignedIpAddress     The assigned ip address
     */
    public void setIpAddress(String assignedIpAddress)
    {
        ipAddress_ = assignedIpAddress;
    }
    
    /**
     * Returns the ip address.
     * 
     * @return  The ip address
     */
    public String getIpAddress()
    {
        return ipAddress_;
    }
}
