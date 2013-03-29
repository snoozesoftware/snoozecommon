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
package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor.HypervisorSettings;
import org.inria.myriads.snoozecommon.communication.localcontroller.wakeup.WakeupSettings;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.globals.Globals;
import org.inria.myriads.snoozecommon.guard.Guard;

/**
 * Local controller description.
 * 
 * @author Eugen Feller
 */
public final class LocalControllerDescription 
    implements Serializable
{        
    /** Default version. */
    private static final long serialVersionUID = 1L;

    /** Identifier of the local controller. */
    private String id_;
    
    /** Status. */
    private LocalControllerStatus status_;
    
    /** Control data address. */
    private NetworkAddress controlDataAddress_;
    
    /** Hostname. */
    private String hostname_;
    
    /** Hypervisor settings. */
    private HypervisorSettings hypervisorSettings_;
    
    /** Wakeup settings. */
    private WakeupSettings wakeupSettings_;
    
    /** Virtual machine meta data. */
    private HashMap<String, VirtualMachineMetaData> virtualMachineMetaData_;
    
    /** Assigned virtual machines. */
    private ArrayList<VirtualMachineMetaData> assignedVirtualMachines_;
    
    /** Total capacity of the local controller. */
    private ArrayList<Double> totalCapacity_;
    
    /**
     * Constructor.
     */
    public LocalControllerDescription()
    {
        super();
        controlDataAddress_ = new NetworkAddress();
        hypervisorSettings_ = new HypervisorSettings();
        wakeupSettings_ = new WakeupSettings();
        virtualMachineMetaData_ = new HashMap<String, VirtualMachineMetaData>();
        assignedVirtualMachines_ = new ArrayList<VirtualMachineMetaData>();
        totalCapacity_ = new ArrayList<Double>();
        hostname_ = initializeHostname();
    }
    
   

    /**
     * Copy constructor.
     * 
     * @param original                      The original description
     * @param numberOfMonitoringEntries     The number of monitoring entries
     * @param withVirtualMachines           True if virtual machines are needed
     */
    public LocalControllerDescription(LocalControllerDescription original, 
            int numberOfMonitoringEntries, 
            boolean withVirtualMachines)
    {
        Guard.check(original, numberOfMonitoringEntries);
        id_ = original.getId();
        status_ = original.getStatus();
        controlDataAddress_ = new NetworkAddress(original.getControlDataAddress());
        hypervisorSettings_ = new HypervisorSettings(original.getHypervisorSettings());
        wakeupSettings_ = new WakeupSettings(original.getWakeupSettings());
        if (withVirtualMachines)
        {
            virtualMachineMetaData_ = original.getVirtualMachineMetaData(numberOfMonitoringEntries);
        }
        else
        {
            virtualMachineMetaData_ = new HashMap<String, VirtualMachineMetaData>();
        }   
        assignedVirtualMachines_ = new ArrayList<VirtualMachineMetaData>();
        totalCapacity_ = new ArrayList<Double>(original.getTotalCapacity());
        hostname_ = original.getHostname();
    }
    
    /**
     * 
     * Initializes the hostname.
     * 
     * @return          The hostname
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
     * @param numberOfMonitoringEntries      The number of monitoring entries
     * @return                               The virtual machine meta data
     */
    private HashMap<String, VirtualMachineMetaData> getVirtualMachineMetaData(int numberOfMonitoringEntries)
    {
        Guard.check(numberOfMonitoringEntries);
        
        HashMap<String, VirtualMachineMetaData> copy = new HashMap<String, VirtualMachineMetaData>();
        for (Map.Entry<String, VirtualMachineMetaData> metaEntry : virtualMachineMetaData_.entrySet())
        {       
            VirtualMachineMetaData metaData = metaEntry.getValue();
            VirtualMachineMetaData copyData = new VirtualMachineMetaData(metaData, numberOfMonitoringEntries);
            String virtualMachineId = metaEntry.getKey();
            copy.put(virtualMachineId, copyData);
        }
        
        return copy;
    }
    
    /**
     * Sets the capacity vector.
     * 
     * @param totalCapacity    The total capacity
     */
    public void setTotalCapacity(ArrayList<Double> totalCapacity)
    {
       totalCapacity_ = totalCapacity; 
    }
    
    /**
     * Returns the total capacity.
     * 
     * @return     List of doubles
     */
    public ArrayList<Double> getTotalCapacity() 
    {
        return totalCapacity_;
    }
    
    /**
     * Sets the id.
     * 
     * @param id   The identifier
     */
    public void setId(String id)
    {
        id_ = id;
    }
    
    /**
     * Returns the local controller identifier.
     * 
     * @return     The identifier
     */
    public String getId() 
    {
        return id_;
    }
    
    /**
     * Returns the control data address.
     * 
     * @return     The control data address
     */
    public NetworkAddress getControlDataAddress() 
    {
        return controlDataAddress_;
    }
    
    /**
     * Sets the control data address.
     * 
     * @param controlDataAddress   The control data address
     */
    public void setControlDataAddress(NetworkAddress controlDataAddress) 
    {
        controlDataAddress_ = controlDataAddress;
    }
    
    /**
     * Sets the hypervisor settings.
     * 
     * @param hypervisorSettings   The hypervisor settings
     */
    public void setHypervisorSettings(HypervisorSettings hypervisorSettings)
    {
        hypervisorSettings_ = hypervisorSettings;
    }
    
    /**
     * Returns the hypervisor port.
     * 
     * @return The hypervisor settings
     */
    public HypervisorSettings getHypervisorSettings() 
    {
        return hypervisorSettings_;
    }
    
    /**
     * Sets the status.
     * 
     * @param status    The status
     */
    public void setStatus(LocalControllerStatus status) 
    {
        status_ = status;
    }
    
    /**
     * Returns the status.
     * 
     * @return  The status
     */
    public LocalControllerStatus getStatus() 
    {
        return status_;
    }
        
    /**
     * Sets virtual machine meta data.
     * 
     * @param virtualMachineMetaData    The meta data
     */
    public void setVirtualMachineMetaData(HashMap<String, VirtualMachineMetaData> virtualMachineMetaData) 
    {
        virtualMachineMetaData_ = virtualMachineMetaData;
    }
    
    /**
     * Returns virtual machine meta data.
     * 
     * @return  The meta data
     */
    public HashMap<String, VirtualMachineMetaData> getVirtualMachineMetaData() 
    {
        return virtualMachineMetaData_;
    }

    /**
     * Sets the wakeup settings.
     * 
     * @param wakeupSettings    The wakeup settings
     */
    public void setWakeupSettings(WakeupSettings wakeupSettings) 
    {
        wakeupSettings_ = wakeupSettings;
    }

    /**
     * Returns the wakeup settings.
     * 
     * @return  The wakeup settings
     */
    public WakeupSettings getWakeupSettings() 
    {
        return wakeupSettings_;
    }

    /**
     * Returns the assigned virtual machines.
     * 
     * @return  The assigned virtual machines
     */
    public ArrayList<VirtualMachineMetaData> getAssignedVirtualMachines()
    {
        return assignedVirtualMachines_;
    }

    /**
     * Sets the assigned virtual machines.
     * 
     * @param assignedVirtualMachines   The assigned virtual machines
     */
    public void setAssignedVirtualMachines(ArrayList<VirtualMachineMetaData> assignedVirtualMachines)
    {
        assignedVirtualMachines_ = assignedVirtualMachines;
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
}
