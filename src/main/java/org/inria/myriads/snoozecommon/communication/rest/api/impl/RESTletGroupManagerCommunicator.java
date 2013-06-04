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
package org.inria.myriads.snoozecommon.communication.rest.api.impl;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.NodeRole;
import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupLeaderRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupManagerRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.localcontroller.AssignedGroupManager;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerList;
import org.inria.myriads.snoozecommon.communication.rest.api.GroupManagerAPI;
import org.inria.myriads.snoozecommon.communication.rest.util.RESTUtil;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.discovery.VirtualMachineDiscoveryResponse;
import org.inria.myriads.snoozecommon.communication.virtualcluster.migration.MigrationRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.requests.MetaDataRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionResponse;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionResponse;
import org.inria.myriads.snoozecommon.communication.virtualmachine.ClientMigrationRequest;
import org.inria.myriads.snoozecommon.communication.virtualmachine.ResizeRequest;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST group manager communicator.
 * 
 * @author Eugen Feller
 */
public final class RESTletGroupManagerCommunicator 
    implements GroupManagerAPI 
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(RESTletGroupManagerCommunicator.class);
    
    /** Group manager address. */
    private NetworkAddress groupManagerAddress_;
    
    /**
     * Constructor.
     * 
     * @param groupManagerAddress  The  group manager address
     */
    public RESTletGroupManagerCommunicator(NetworkAddress groupManagerAddress) 
    {
        Guard.check(groupManagerAddress);
        log_.debug("Initializing REST group manager communicator");
        groupManagerAddress_ = groupManagerAddress;
    }

    /**
     * Creates a client resource.
     * 
     * @return     The client resource
     */
    private ClientResource createClientResource()
    {
        log_.debug("Creating client resource");
        ClientResource clientResource = RESTUtil.createClientResource(NodeRole.groupmanager, 
                                                                       groupManagerAddress_);
        return clientResource;
    }

    /**
     * Send a join request to the group leader.
     * (called by a group manager)
     * 
     * @param groupManagerDescription   The group manager description
     * @return                          true if everything ok, else otherwise
     */
    @Override
    public boolean joinGroupLeader(GroupManagerDescription groupManagerDescription)
    {
        Guard.check(groupManagerDescription);
        log_.debug(String.format("Sending group manager join request to the groupleader: %s",
                                 groupManagerDescription.getId()));
   
        ClientResource clientResource = null;
        boolean hasJoined = false;
        try 
        {
            clientResource = createClientResource();
            GroupManagerAPI  groupLeaderResource = clientResource.wrap(GroupManagerAPI.class); 
            hasJoined = groupLeaderResource.joinGroupLeader(groupManagerDescription);  
        } 
        catch (Exception exception)
        {
            log_.debug(String.format("Error joining group leader: %s", exception.getMessage()));
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
    
        return hasJoined;
    }
    
    /** 
     * Assign a local controller to a group manager.
     * (called by a local controller)
     * 
     * @param localControllerDescription   The local controller description
     * @return                             The local controller assignment
     */
    @Override
    public AssignedGroupManager assignLocalController(LocalControllerDescription localControllerDescription) 
    {
        Guard.check(localControllerDescription);
        log_.debug("Sending a local controller to group manager assignment request to the group leader");
        
        ClientResource clientResource = null;
        AssignedGroupManager assignment = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupLeaderResource = clientResource.wrap(GroupManagerAPI.class); 
            assignment = groupLeaderResource.assignLocalController(localControllerDescription);
        }
        catch (Exception exception)
        {
            log_.debug(String.format("Exception during group leader communication: %s", 
                                     exception.getMessage()));
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return assignment;
    }
    
    /**
     * Send a request to start the virtual cluster to the group leader.
     *  
     * @param virtualClusterDescription   The virtual cluster description
     * @return                            The task identifier
     */
    @Override
    public String startVirtualCluster(VirtualClusterSubmissionRequest virtualClusterDescription) 
    {
        Guard.check(virtualClusterDescription);
        log_.debug("Sending virtual cluster start request to group leader");
                  
        ClientResource clientResource = null;
        String taskIdentifier = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupLeaderResource = clientResource.wrap(GroupManagerAPI.class);
            taskIdentifier = groupLeaderResource.startVirtualCluster(virtualClusterDescription);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group leader", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return taskIdentifier;
    }
    
    /**
     * Returns the repository information.
     * 
     * @param numberOfBacklogEntries    The number of backlog entries
     * @return                          The group leader repository information
     */
    @Override
    public GroupLeaderRepositoryInformation getGroupLeaderRepositoryInformation(int numberOfBacklogEntries) 
    {
        log_.debug("Sending repository information retrieval request");
          
        ClientResource clientResource = null;
        GroupLeaderRepositoryInformation repositoryInformation = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupLeaderResource = clientResource.wrap(GroupManagerAPI.class);
            repositoryInformation = groupLeaderResource.getGroupLeaderRepositoryInformation(numberOfBacklogEntries);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group leader", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return repositoryInformation;
    }    
    
    /**
     * Routine to discover a local controller.
     * 
     * @param virtualMachineId      The virtual machine identifier
     * @return                      The group manager description
     */
    @Override
    public VirtualMachineDiscoveryResponse discoverVirtualMachine(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug("Sending virtual machine group manager discovery request");       
        
        ClientResource clientResource = null;
        VirtualMachineDiscoveryResponse discoveryResponse = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupLeaderResource = clientResource.wrap(GroupManagerAPI.class);
            discoveryResponse = groupLeaderResource.discoverVirtualMachine(virtualMachineId);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group leader", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return discoveryResponse;        
    }
    
    /**
     * Returns the virtual cluster response if available.
     * 
     * @param taskIdentifier  The task identifier
     * @return                The virtual cluster response
     */
    @Override
    public VirtualClusterSubmissionResponse getVirtualClusterResponse(String taskIdentifier) 
    {
        Guard.check(taskIdentifier);
        log_.debug("Sending virtual cluster reponse lookup request");
        
        ClientResource clientResource = null;
        VirtualClusterSubmissionResponse virtualClusterResponse = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupLeaderResource = clientResource.wrap(GroupManagerAPI.class);
            virtualClusterResponse = groupLeaderResource.getVirtualClusterResponse(taskIdentifier);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group leader", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return virtualClusterResponse;
    }
    
    /**
     * Called by local controller to join the group manager.
     *  
     * @param localControllerDescription    The local controller description
     * @return                              true if everything ok, false otherwise
     */
    @Override
    public boolean joinGroupManager(LocalControllerDescription localControllerDescription) 
    {
        Guard.check(localControllerDescription);
        log_.debug("Sending local controller join request to group manager");
        
        ClientResource clientResource = null;
        boolean hasJoined = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            hasJoined = groupManagerResource.joinGroupManager(localControllerDescription);      
        } 
        catch (Exception exception)
        {
            log_.debug("Error joining group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return hasJoined;
    }
    
    /**
     * Starts a virtual machine on the group manager.
     * (called by group leader)
     * 
     * @param submissionRequest      The virtual machine submission request
     * @return                       The task identifier
     */
    public String startVirtualMachines(VirtualMachineSubmissionRequest submissionRequest) 
    {
        Guard.check(submissionRequest);  
        log_.debug("Starting virtual machines"); 
        
        ClientResource clientResource = null;
        String taskIdentifier = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            taskIdentifier = groupManagerResource.startVirtualMachines(submissionRequest);     
        }
        catch (Exception exception)
        {
            log_.debug("Error starting virtual machine", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return taskIdentifier;
    }
    
    /**
     * Check for virtual machine availability.
     * 
     * @param virtualMachineId  The virtual machine identifier
     * @return                  The local controller identigier
     */
    @Override
    public String searchVirtualMachine(String virtualMachineId) 
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Searching for local controller of virtual machine %s", virtualMachineId));
        
        ClientResource clientResource = null;
        String localControllerId = null;
        
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            localControllerId = groupManagerResource.searchVirtualMachine(virtualMachineId);
        }
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine lookup", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return localControllerId;
    }
    
    /**
     * Check for virtual machine availability.
     * 
     * @param location   The virtual machine location
     * @return           true if everything ok, false otherwise
     */
    @Override
    public boolean hasVirtualMachine(VirtualMachineLocation location) 
    {
        Guard.check(location);
        log_.debug(String.format("Checking if virtual machine %s is still on local controller %s", 
                                 location.getVirtualMachineId(), location.getLocalControllerId()));
        
        ClientResource clientResource = null;
        boolean hasVirtualMachine = false;
        
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            hasVirtualMachine = groupManagerResource.hasVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine lookup", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return hasVirtualMachine;
    }
    
    /**
     * Suspends the energy saver.
     * 
     * @return      true if everything ok, false otherwise
     */
    @Override
    public boolean suspendEnergySaver() 
    {
        log_.debug("Sending energy saver suspend request");       
        
        ClientResource clientResource = null;
        boolean isSuspended = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isSuspended = groupManagerResource.suspendEnergySaver();
        }
        catch (Exception exception)
        {
            log_.debug("Error suspending energy saver", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isSuspended;   
    }
    
    /**
     * Resumes the energy saver.
     *
     * @return      true if everything ok, false otherwise
     */
    @Override
    public boolean resumeEnergySaver() 
    {
        log_.debug("Sending energy saver resume request");
        
        ClientResource clientResource = null;
        boolean isResumed = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isResumed = groupManagerResource.resumeEnergySaver();
        }
        catch (Exception exception)
        {
            log_.debug("Error resume energy saver", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isResumed;         
    }
    
    /**
     * Routine to shutdown a virtual machine.
     * 
     * @param location   The virtual machine location
     * @return           true if everything ok, false otherwise
     */
    @Override
    public boolean shutdownVirtualMachine(VirtualMachineLocation location)
    {
        Guard.check(location);
        log_.debug(String.format("Sending virtual machine %s shutdown command to group manager", 
                                 location.getVirtualMachineId(), location.getLocalControllerId()));
        
        ClientResource clientResource = null;
        boolean isShutdown = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isShutdown = groupManagerResource.shutdownVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isShutdown;       
    }
    
    /**
     * Routine to reboot a virtual machine.
     * 
     * @param location   The virtual machine location
     * @return           true if everything ok, false otherwise
     */
    @Override
    public boolean rebootVirtualMachine(VirtualMachineLocation location)
    {
        Guard.check(location);
        log_.debug(String.format("Sending virtual machine %s reboot command to group manager", 
                                 location.getVirtualMachineId(), location.getLocalControllerId()));
        
        ClientResource clientResource = null;
        boolean isRebooted = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isRebooted = groupManagerResource.rebootVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isRebooted;       
    }
    
    /**
     * Routine to shutdown (hard shutdown) a virtual machine.
     * 
     * @param location   The virtual machine location
     * @return           true if everything ok, false otherwise
     */
    @Override
    public boolean destroyVirtualMachine(VirtualMachineLocation location)
    {
        Guard.check(location);
        log_.debug(String.format("Sending virtual machine %s destroy command to group managers local controller: %s", 
                                 location.getVirtualMachineId(),
                                 location.getLocalControllerId()));
        
        ClientResource clientResource = null;
        boolean isDestroyed = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isDestroyed = groupManagerResource.destroyVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isDestroyed;     
    }
    
    /**
     * Routine to get virtual machine meta data.
     * 
     * @param request      The virtual machine meta data request
     * @return             The virtual machine meta data
     */
    @Override
    public VirtualMachineMetaData getVirtualMachineMetaData(MetaDataRequest request)
    {
        Guard.check(request);
        log_.debug(String.format("Sending virtual machine %s information request to group manager", 
                                 request.getVirtualMachineLocation().getVirtualMachineId())); 
        
        ClientResource clientResource = null;
        VirtualMachineMetaData metaData = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            metaData = groupManagerResource.getVirtualMachineMetaData(request);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return metaData;
    }
    
    /**
     * Routine to resume a virtual machine.
     * 
     * @param location     The virtual machine location
     * @return             true if everything ok, false otherwise
     */
    @Override
    public boolean resumeVirtualMachine(VirtualMachineLocation location)
    {
        Guard.check(location);
        log_.debug(String.format("Sending virtual machine: %s resume command to group manager", 
                                 location.getVirtualMachineId()));   
        
        ClientResource clientResource = null;
        boolean isResumed = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isResumed = groupManagerResource.resumeVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isResumed;
    }
    
    /**
     * Routine to suspend a virtual machine.
     * 
     * @param location     The virtual machine location
     * @return             true if everything ok, false otherwise
     */
    @Override
    public boolean suspendVirtualMachine(VirtualMachineLocation location)
    {
        Guard.check(location);
        log_.debug(String.format("Sending virtual machine: %s suspend command to group manager", 
                                 location.getVirtualMachineId()));
        
        ClientResource clientResource = null;
        boolean isSuspended = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isSuspended = groupManagerResource.suspendVirtualMachine(location);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isSuspended;
    }
            
    /**
     * Returns the repository information.
     * 
     * @param numberOfBacklogEntries    The number of backlog entries
     * @return                          The group manager repository information
     */
    @Override
    public GroupManagerRepositoryInformation getGroupManagerRepositoryInformation(int numberOfBacklogEntries) 
    {
        log_.debug("Sending repository information retrieval request");
        
        ClientResource clientResource = null;
        GroupManagerRepositoryInformation repositoryInformation = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            repositoryInformation = groupManagerResource.getGroupManagerRepositoryInformation(numberOfBacklogEntries);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        log_.debug(String.format("Returning reference: %s", repositoryInformation));
        return repositoryInformation;
    }
    
    /**
     * Drops virtual machine meta data.
     * 
     * @param virtualMachineLocation        The virtual machine location
     * @return                              true if everything ok, false otherwise
     */
    @Override
    public boolean dropVirtualMachineMetaData(VirtualMachineLocation virtualMachineLocation)
    {
        Guard.check(virtualMachineLocation);
        log_.debug(String.format("Sending virtual machine %s meta data drop request to group manager", 
                                 virtualMachineLocation.getVirtualMachineId()));     
        
        ClientResource clientResource = null;
        boolean isDropped = false;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            isDropped = groupManagerResource.dropVirtualMachineMetaData(virtualMachineLocation);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return isDropped;        
    }
    
    /**
     * Checks if local controller exists.
     * 
     * @param localControllerAddress     The lcoal controller address
     * @return                           The previous identifier, null otherwise
     */
    @Override
    public String hasLocalController(NetworkAddress localControllerAddress)
    {
        Guard.check(localControllerAddress);
        log_.debug(String.format("Sending local controller %s: %d lookup request to group manager", 
                                 localControllerAddress.getAddress(), localControllerAddress.getPort()));     
        
        ClientResource clientResource = null;
        String localControllerId = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            localControllerId = groupManagerResource.hasLocalController(localControllerAddress);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return localControllerId;        
    }
    
    /**
     * Returns the virtual machine response.
     * 
     * @param taskIdentifier    The task identifier
     * @return                  The virtual machine submission response
     */
    @Override
    public VirtualMachineSubmissionResponse getVirtualMachineSubmissionResponse(String taskIdentifier) 
    {
        Guard.check(taskIdentifier);
        log_.debug(String.format("Sending virtual machine %s response lookup reuest to group manager", 
                taskIdentifier));     
        
        ClientResource clientResource = null;
        VirtualMachineSubmissionResponse response = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            response = groupManagerResource.getVirtualMachineSubmissionResponse(taskIdentifier);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return response;   
    }

    /**
     * Migrate a virtual machine.
     * (call by the client)
     * @param clientMigrationRequest     The client migration Request
     * @return                           true if ok false otherwise
     */
    public boolean migrateVirtualMachine(MigrationRequest migrationRequest) 
    {
        log_.debug("Sending a migration request");
        ClientResource clientResource = null;
        boolean response = false; 
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            response = groupManagerResource.migrateVirtualMachine(migrationRequest);
        }
        catch (Exception exception)
        {
            log_.debug("Error while migrating  the virtual machine", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return response;
    }

    /**
     * 
     * Gets the list of local controllers.
     * 
     * @return      The local controller list
     */
    public LocalControllerList getLocalControllerList()
    {
        log_.debug("Sending local controller list request");     
        
        ClientResource clientResource = null;
        LocalControllerList response = null;
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            response = groupManagerResource.getLocalControllerList();
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return response; 
    }

    /**
     * Resize a virtual machine.
     * (call by the client)
     * not implemented here...
     * 
     * @param resizeRequest          The client resize Request
     * @return                       true if ok false otherwise
     */
    public VirtualMachineMetaData resizeVirtualMachine(ResizeRequest resizeRequest)
    {
        log_.debug("Sending local controller list request");     
        
        ClientResource clientResource = null;
        
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            return  groupManagerResource.resizeVirtualMachine(resizeRequest);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return null;
    }

    /** 
     * 
     */
    @Override
    
    public boolean addVirtualMachineAfterMigration(VirtualMachineMetaData virtualMachine) 
    {
        log_.debug("Sending add virtual machine request to groupmanager");     
        
        ClientResource clientResource = null;
        
        try
        {
            clientResource = createClientResource();
            GroupManagerAPI groupManagerResource = clientResource.wrap(GroupManagerAPI.class);
            return  groupManagerResource.addVirtualMachineAfterMigration(virtualMachine);
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting group manager", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return false;
    }

}
