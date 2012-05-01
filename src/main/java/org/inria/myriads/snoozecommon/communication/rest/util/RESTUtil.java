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
package org.inria.myriads.snoozecommon.communication.rest.util;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.NodeRole;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST utility.
 * 
 * @author Eugen Feller
 */
public final class RESTUtil 
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(RESTUtil.class);
    
    /** Hide constructor. */
    private RESTUtil()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Creates a web resource.
     * 
     * @param nodeRole          The node role
     * @param networkAddress    The network address
     * @return                  The web resource
     */
    public static ClientResource createClientResource(NodeRole nodeRole, NetworkAddress networkAddress)
    {
        Guard.check(nodeRole, networkAddress);
        log_.debug(String.format("Generating client resource: %s", nodeRole));
        
        String uri = null;
        String listenAddress = networkAddress.getAddress();
        String controlDataPort = String.valueOf(networkAddress.getPort());
        switch (nodeRole)
        {
            case bootstrap :
                uri = "http://" + listenAddress + ":" + controlDataPort + "/bootstrap";
                break;
                
            case groupmanager :
                uri = "http://" + listenAddress + ":" + controlDataPort + "/groupmanager";
                break;
                
            case localcontroller :
                uri = "http://" + listenAddress + ":" + controlDataPort + "/localcontroller";
                break;
                
            default :
                log_.error("Unknown node role selected");
                return null;
        }   

        ClientResource clientResource = new ClientResource(uri);        
        log_.debug(String.format("Constructed resource URI is: %s", uri));   
        return clientResource;
    }
}
