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
package org.inria.myriads.snoozecommon.communication.groupmanager;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.groupmanager.summary.GroupManagerSummaryInformation;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.datastructure.LRUCache;
import org.inria.myriads.snoozecommon.globals.Globals;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Group manager description.
 * 
 * @author Eugen Feller
 */
public class GroupManagerDescription 
    implements Serializable
{            
    /** Logger. */
    private static final Logger log_ = LoggerFactory.getLogger(GroupManagerDescription.class);
    
    /** Default uid. */
    private static final long serialVersionUID = 1L;
    
    /** Identifier. */
    private String id_;
            
    /** Heartbeat address. */
    private NetworkAddress heartbeatAddress_;
    
    /** Listen settings. */
    private ListenSettings listenSettings_;
    
    /** Hostname.*/
    private String hostname_;
    
    /** isAssigned to a running GroupLeader.*/
    private boolean isAssigned_;
       
    /** Assigned virtual machines. */
    private ArrayList<VirtualMachineMetaData> virtualMachines_;
    
    /** Summary monitoring data. */
    private LRUCache<Long, GroupManagerSummaryInformation> summary_;
    
    /** Local controller descriptions. */
    private HashMap<String, LocalControllerDescription> localControllers_;
     
    /** Empty constructor. */
    public GroupManagerDescription()
    {
        heartbeatAddress_ = new NetworkAddress();
        listenSettings_ = new ListenSettings();
        virtualMachines_ = new ArrayList<VirtualMachineMetaData>();
        summary_ = new LRUCache<Long, GroupManagerSummaryInformation>();
        localControllers_ = new HashMap<String, LocalControllerDescription>();
        hostname_ = initializeHostname();
        isAssigned_ = false;
    }
       
  
    /**
     * Copy constructor.
     * 
     * @param groupManager              The group manager description
     * @param numberOfBacklogEntries    The number of backlog entries
     */
    public GroupManagerDescription(GroupManagerDescription groupManager, int numberOfBacklogEntries)
    {
        Guard.check(groupManager, numberOfBacklogEntries);
        id_ = groupManager.getId();
        heartbeatAddress_ = new NetworkAddress(groupManager.getHeartbeatAddress());
        listenSettings_ = new ListenSettings(groupManager.getListenSettings());
        virtualMachines_ = new ArrayList<VirtualMachineMetaData>();
        summary_ = groupManager.getGroupManagerSummaryData(numberOfBacklogEntries);
        localControllers_ = new HashMap<String, LocalControllerDescription>(groupManager.getLocalControllers());
        hostname_ = groupManager.getHostname();
        isAssigned_ = groupManager.getIsAssigned();
    }

    
    /**
     * 
     * Initializes the hostname.
     * 
     * @return      The hostname                
     */
    private String initializeHostname()
    {
        String hostname = null;
        try 
        {
          final InetAddress addr = InetAddress.getLocalHost();
          hostname = new String(addr.getHostName());
        } 
        catch (final Exception e) 
        {
            hostname = Globals.DEFAULT_INITIALIZATION;
        }
        return hostname;
    }

    /**
     * Returns the algorithm virtual machine meta data.
     * 
     * @param numberOfMonitoringEntries     The number of monitoring entries
     * @return                              The group manager summary data
     */
    private LRUCache<Long, GroupManagerSummaryInformation> getGroupManagerSummaryData(int numberOfMonitoringEntries)
    {
        Guard.check(numberOfMonitoringEntries);
        
        LRUCache<Long, GroupManagerSummaryInformation> result = 
            new LRUCache<Long, GroupManagerSummaryInformation>(numberOfMonitoringEntries);  
        
        // Indeed we want the n most recent values -> Reverse the list
        List<GroupManagerSummaryInformation> reverseList = Lists.reverse(Lists.newArrayList(summary_.values()));
        for (GroupManagerSummaryInformation summaryInformation : reverseList)
        {
            GroupManagerSummaryInformation copiedEntity = new GroupManagerSummaryInformation(summaryInformation);
            if (result.size() == numberOfMonitoringEntries)
            {
                break;
            }
            
            log_.debug(String.format("Copied group manager summary data. Time: %s, active: %s, and used capacity: %s", 
                                     copiedEntity.getTimeStamp(),
                                     copiedEntity.getActiveCapacity(),
                                     copiedEntity.getUsedCapacity())); 
            result.put(copiedEntity.getTimeStamp(), copiedEntity);
        }
        
        return result;
    }
    
    /**
     * Returns the listen settings.
     * 
     * @param listenSettings     The listen settings
     */
    public void setListenSettings(ListenSettings listenSettings) 
    {
        listenSettings_ = listenSettings;
    }
    
    /**
     * Returns the listen settings.
     * 
     * @return     The listen settings
     */
    public ListenSettings getListenSettings() 
    {
        return listenSettings_;
    }
    
    /**
     * Sets the heartbeat multicast port.
     * 
     * @param heartbeatAddress   The heartbeat multicast port
     */
    public void setHeartbeatAddress(NetworkAddress heartbeatAddress) 
    {
        heartbeatAddress_ = heartbeatAddress;
    }
    
    /**
     * Returns the heartbeat address.
     * 
     * @return         The heartbeat address
     */
    public NetworkAddress getHeartbeatAddress() 
    {
        return heartbeatAddress_;
    }
    
    /**
     * Sets the identifier.
     * 
     * @param id       The identifier
     */
    public void setId(String id) 
    {
        id_ = id;
    }
    
    /**
     * Returns the identifier.
     * 
     * @return     The id
     */
    public String getId() 
    {
        return id_;
    }
        
    /**
     * Sets the group manager summary information.
     * 
     * @param summaryInformation   The sumamey information
     */
    public void setSummaryInformation(LRUCache<Long, GroupManagerSummaryInformation> summaryInformation) 
    {
        summary_ = summaryInformation;
    }
    
    /**
     * Returns the group manager summary information.
     * 
     * @return  The group manager summary information
     */
    public LRUCache<Long, GroupManagerSummaryInformation> getSummaryInformation() 
    {
        return summary_;
    }
    
    /**
     * Sets the local controller descriptions.
     * 
     * @param localControllers   The local controller descriptions
     */
    public void setLocalControllers(HashMap<String, LocalControllerDescription> localControllers) 
    {
        localControllers_ = localControllers;
    }
    
    /**
     * Sets the local controller descriptions.
     * 
     * @param localControllers   The local controller descriptions
     */
    public void setLocalControllersFromArray(ArrayList<LocalControllerDescription> localControllers) 
    {
        for (LocalControllerDescription localController : localControllers)
        {
            localControllers_.put(localController.getId(), localController);
        }
    }
    
    /**
     * Returns the local controller descriptions.
     * 
     * @return  The local controller descriptions
     */
    public HashMap<String, LocalControllerDescription> getLocalControllers() 
    {
        return localControllers_;
    }
    
    /**
     * Sets the virtual machines.
     * 
     * @param virtualMachines   The virtual machines
     */
    public void setVirtualMachines(ArrayList<VirtualMachineMetaData> virtualMachines) 
    {
        virtualMachines_ = virtualMachines;
    }
    
    /**
     * Returns the virtual machines.
     * 
     * @return  The virtual machines
     */
    public ArrayList<VirtualMachineMetaData> getVirtualMachines() 
    {
        return virtualMachines_;
    }

    /**
     * @return the hostname
     */
    public String getHostname() 
    {
        return hostname_;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) 
    {
        hostname_ = hostname;
    }


    /**
     * @return the isAssigned
     */
    public boolean getIsAssigned() 
    {
        return isAssigned_;
    }


    /**
     * @param isAssigned the isAssigned to set
     */
    public void setIsAssigned(boolean isAssigned) 
    {
        isAssigned_ = isAssigned;
    }
}
