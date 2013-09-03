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

import java.util.List;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.NodeRole;
import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupLeaderRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerList;
import org.inria.myriads.snoozecommon.communication.rest.api.BootstrapAPI;
import org.inria.myriads.snoozecommon.communication.rest.util.RESTUtil;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.migration.ClientMigrationRequestSimple;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.inria.myriads.snoozecommon.request.HostListRequest;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST bootstrap communicator class.
 * 
 * @author Eugen Feller
 */
public final class RESTletBootstrapCommunicator 
    implements BootstrapAPI
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(RESTletBootstrapCommunicator.class);
    
    /** Bootstrap address. */
    private NetworkAddress bootstrapAddress_;
        
    /**
     * Bootstrap communicator constructor.
     * 
     * @param bootstrapAddress    The bootstrap address
     */
    public RESTletBootstrapCommunicator(NetworkAddress bootstrapAddress)
    {
        Guard.check(bootstrapAddress);
        log_.debug("Initializing REST group leader communicator");
        bootstrapAddress_ = bootstrapAddress;
    }
    
    /**
     * Creates a client resource.
     * 
     * @return  The client resource
     */
    private ClientResource createClientResource()
    {
        ClientResource clientResource = RESTUtil.createClientResource(NodeRole.bootstrap, bootstrapAddress_);
        return clientResource;
    }
    
    /**
     * Request the current group leader.
     * 
     * @return      Current group leader description
     */
    @Override
    public GroupManagerDescription getGroupLeaderDescription()
    {
        log_.debug("Requesting group leader from bootstrap node");
        
        ClientResource clientResource = null;
        GroupManagerDescription groupManagerDescription = null;
        try
        {
            clientResource = createClientResource();
            BootstrapAPI bootstrapResource = clientResource.wrap(BootstrapAPI.class); 
            groupManagerDescription = bootstrapResource.getGroupLeaderDescription();
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting bootstrap", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return groupManagerDescription;    
    }
    
    /**
     * Request the current group leader.
     * 
     * @return      Current group leader description
     */
    @Override
    public GroupLeaderRepositoryInformation getCompleteHierarchy()
    {
        log_.debug("Requesting group leader from bootstrap node");
        
        ClientResource clientResource = null;
        GroupLeaderRepositoryInformation hierarchy = null;
        try
        {
            clientResource = createClientResource();
            BootstrapAPI bootstrapResource = clientResource.wrap(BootstrapAPI.class); 
            hierarchy = bootstrapResource.getCompleteHierarchy();
        }
        catch (Exception exception)
        {
            log_.debug("Error while contacting bootstrap", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return hierarchy;    
    }

    @Override
    @Post("?destroyVirtualMachine")
    public boolean destroyVirtualMachine(String virtualMachineId) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    @Post("?suspendVirtualMachine")
    public boolean suspendVirtualMachine(String virtualMachineId) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    public boolean rebootVirtualMachine(String id) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    
    public boolean shutdownVirtualMachine(String virtualMachineId) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    public boolean resumeVirtualMachine(String virtualMachineId) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    public boolean migrateVirtualMachine(ClientMigrationRequestSimple migrationRequest) 
    {
        log_.error("Not implemented yet");
        return false;
    }

    @Override
    public String startVirtualCluster(VirtualClusterSubmissionRequest virtualClusterDescription) 
    {
        log_.error("Not implemented yet");
        return null;
    }

    @Override    
    public List<GroupManagerDescription> getGroupManagerDescriptions(HostListRequest hostListRequest) 
    {
        log_.error("Not implemented yet");
        return null;
    }

    @Override
    public LocalControllerList geLocalControllerList() 
    {
        log_.error("Not implemented yet");
        return null;
    }

    @Override
    
    public List<LocalControllerDescription> getLocalControllerDescriptions(HostListRequest hostListRequest) 
    {
        return null;
    }

    @Override
    public List<VirtualMachineMetaData> getVirtualMachineDescriptions(HostListRequest hostListRequest) 
    {
        return null;
    }
}
