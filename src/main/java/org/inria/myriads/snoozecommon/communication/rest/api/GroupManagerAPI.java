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
package org.inria.myriads.snoozecommon.communication.rest.api;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupLeaderRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupManagerRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.localcontroller.AssignedGroupManager;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.discovery.VirtualMachineDiscoveryResponse;
import org.inria.myriads.snoozecommon.communication.virtualcluster.requests.MetaDataRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionResponse;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionResponse;
import org.inria.myriads.snoozecommon.communication.virtualmachine.ClientMigrationRequest;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Group manager resource interface.
 * 
 * @author Eugen Feller
 */
public interface GroupManagerAPI
{    
    /**
     * Dispatches the virtual cluster submission request.
     * (called by the client)
     * 
     * @param  virtualClusterDescription  The virtual cluster description
     * @return                            The virtual cluster mapping
     */
    @Post("?startVirtualCluster")
    String startVirtualCluster(VirtualClusterSubmissionRequest virtualClusterDescription); 
        
    /**
     * Returns the virtual machine response.
     * 
     * @param taskIdentifier    The task identifier
     * @return                  The virtual machine submission response
     */
    @Post("?getVirtualMachineSubmissionResponse")
    VirtualMachineSubmissionResponse getVirtualMachineSubmissionResponse(String taskIdentifier);
    
    /**
     * Returns the virtual cluster response.
     * 
     * @param taskIdentifier    The task identifier
     * @return                  The virtual cluster response
     */
    @Post("?getVirtualClusterResponse")
    VirtualClusterSubmissionResponse getVirtualClusterResponse(String taskIdentifier);
    
    /** 
     * Handles the join request of a group manager.
     * (called by a group manager) 
     * 
     * @param  groupManagerDescription   The group manager description
     * @return                           true if everything ok, false otherwise
     */     
    @Post("?joinGroupLeader")
    boolean joinGroupLeader(GroupManagerDescription groupManagerDescription); 
       
    /** 
     * Routine to join the group manager.
     * (called by a local controller)
     *  
     * @param  localControllerDescription  The local controller description
     * @return                             true if everything ok, false otherwise
     */ 
    @Post("?joinGroupManager")
    boolean joinGroupManager(LocalControllerDescription localControllerDescription); 
    
    /** 
     * Assign local controller to a group manager.
     * (called by the local controller)
     *  
     * @param  localControllerDescription     The local controller description
     * @return                                The local controller assignment
     */
    @Post("?assignLocalController")
    AssignedGroupManager assignLocalController(LocalControllerDescription localControllerDescription);
    
    /**
     * Routine to discover the group manager hosting a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   The local controller information
     */
    @Post("?discoverVirtualMachine")
    VirtualMachineDiscoveryResponse discoverVirtualMachine(String virtualMachineId); 
       
    /**
     * Routine the group leader information.
     * 
     * @param numberOfMonitoringEntries    The number of monitoring entries
     * @return                             The group leader repository information
     */
    @Post("?getGroupLeaderRepositoryInformation")
    GroupLeaderRepositoryInformation getGroupLeaderRepositoryInformation(int numberOfMonitoringEntries); 

    /**
     * Return the group leader information.
     * 
     * @param numberOfMonitoringEntries    The number of monitoring entries
     * @return                             The group manager repository information
     */
    @Post("?getGroupManagerRepositoryInformation")
    GroupManagerRepositoryInformation getGroupManagerRepositoryInformation(int numberOfMonitoringEntries); 
    
    /**
     * Starts virtual machines on the group manager.
     * 
     * @param submissionRequest      The virtual machine submission request
     * @return                       The task identifier
     */
    @Post("?startVirtualMachines")
    String startVirtualMachines(VirtualMachineSubmissionRequest submissionRequest); 
                
    /**
     * Routine to suspend a virtual machine.
     * 
     * @param location    The virtual machine location
     * @return            true if everything ok, false otherwise
     */
    @Post("?suspendVirtualMachine")
    boolean suspendVirtualMachine(VirtualMachineLocation location);
    
    /**
     * Routine to resume a virtual machine.
     * 
     * @param location    The virtual machine location
     * @return            true if everything ok, false otherwise
     */
    @Post("?resumeVirtualMachine")
    boolean resumeVirtualMachine(VirtualMachineLocation location);
    
    /**
     * Routine to shutdown a virtual machine.
     * 
     * @param location    The virtual machine location
     * @return            true if everything ok, false otherwise
     */
    @Post("?shutdownVirtualMachine")
    boolean shutdownVirtualMachine(VirtualMachineLocation location);
    
    /**
     * Routine to reboot a virtual machine.
     * 
     * @param location    The virtual machine location
     * @return            true if everything ok, false otherwise
     */
    @Post("?rebootVirtualMachine")
    boolean rebootVirtualMachine(VirtualMachineLocation location);
    
    /**
     * Routine to shutdown a virtual machine.
     * 
     * @param location    The management request
     * @return            true if everything ok, false otherwise
     */
    @Post("?destroyVirtualMachine") 
    boolean destroyVirtualMachine(VirtualMachineLocation location);
    
    /**
     * Returns the local controller identifier of a virtual machine.
     * 
     * @param virtualMachineId     The virtual machine identifier
     * @return                     The local controller identifier
     */
    @Post("?getLocalControllerDescription") 
    String searchVirtualMachine(String virtualMachineId);
        
    /**
     * Checks if a virtual machine is active on a particular local controller.
     * 
     * @param location    The virtual machine location
     * @return            true if everything ok, false otherwise
     */
    @Post("?hasVirtualMachine") 
    boolean hasVirtualMachine(VirtualMachineLocation location);
        
    /**
     * Routine to get virtual machine meta data.
     * 
     * @param request      The meta data request
     * @return             The virtual machine information
     */
    @Post("?getVirtualMachineMetaData") 
    VirtualMachineMetaData getVirtualMachineMetaData(MetaDataRequest request); 
                        
    /**
     * Suspends the energy saver.
     * 
     * @return     true if everything ok, false otherwise
     */
    @Get("?suspendEnergySaver")
    boolean suspendEnergySaver(); 
    
    /**
     * Resumes the energy saver.
     * 
     * @return     true if everything ok, false otherwise
     */
    @Get("?resumeEnergySaver")
    boolean resumeEnergySaver();
    
    /**
     * Drops virtual machine met data.
     * 
     * @param virtualMachineLocation        The virtual machine location
     * @return                              true if everything ok, false otherwise
     */
    @Post("?dropVirtualMachineMetaData") 
    boolean dropVirtualMachineMetaData(VirtualMachineLocation virtualMachineLocation);

    /**
     * Checks if local controller exists.
     * 
     * @param localControllerAddress     The lcoal controller address
     * @return                           The previous identifier, null otherwise
     */
    @Post("?hasLocalController")
    String hasLocalController(NetworkAddress localControllerAddress);
    
    
    /**
     * Migrate a virtual machine.
     * (call by the client)
     * 
     * @param clientMigrationRequest     The client migration Request
     * @return                           true if ok false otherwise
     */
    @Post("?migrateVirtualMachine")
    boolean migrateVirtualMachine(ClientMigrationRequest clientMigrationRequest);
    
}

