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
package org.inria.myriads.snoozecommon.communication.rest;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.rest.api.BootstrapAPI;
import org.inria.myriads.snoozecommon.communication.rest.api.GroupManagerAPI;
import org.inria.myriads.snoozecommon.communication.rest.api.LocalControllerAPI;
import org.inria.myriads.snoozecommon.communication.rest.api.impl.RESTLocalControllerCommunicator;
import org.inria.myriads.snoozecommon.communication.rest.api.impl.RESTletBootstrapCommunicator;
import org.inria.myriads.snoozecommon.communication.rest.api.impl.RESTletGroupManagerCommunicator;

/**
 * Component communicator factory.
 * 
 * @author Eugen Feller
 */
public final class CommunicatorFactory 
{
    /** Hide constructor. */
    private CommunicatorFactory()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Creates a new bootstrap communicator instance.
     * 
     * @param bootstrapAddress     The bootstrap address
     * @return                     The bootstrap communicator instance
     */
    public static BootstrapAPI newBootstrapCommunicator(NetworkAddress bootstrapAddress) 
    {
        return new RESTletBootstrapCommunicator(bootstrapAddress);
    }
    
    /** 
     * Returns the group manager communicator object.
     *  
     * @param groupManagerAddress      The group manager address
     * @return                         The group manager communicator
     */
    public static GroupManagerAPI newGroupManagerCommunicator(NetworkAddress groupManagerAddress) 
    {
        return new RESTletGroupManagerCommunicator(groupManagerAddress);
    }
    
    /**
     * Returns the local controller communicator object.
     * 
     * @param localControllerAddress     The local controller address
     * @return                           The local controller communicator
     */
    public static LocalControllerAPI newLocalControllerCommunicator(NetworkAddress localControllerAddress)
    {
        return new RESTLocalControllerCommunicator(localControllerAddress);
    }
}
