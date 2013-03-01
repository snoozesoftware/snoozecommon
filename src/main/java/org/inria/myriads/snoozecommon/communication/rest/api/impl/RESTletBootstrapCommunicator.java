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
import org.inria.myriads.snoozecommon.communication.rest.api.BootstrapAPI;
import org.inria.myriads.snoozecommon.communication.rest.util.RESTUtil;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.restlet.resource.ClientResource;
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
}
