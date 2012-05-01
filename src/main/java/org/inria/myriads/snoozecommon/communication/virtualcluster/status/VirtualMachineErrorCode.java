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
package org.inria.myriads.snoozecommon.communication.virtualcluster.status;

/**
 * Virtual machine error codes.
 * 
 * @author Eugen Feller
 */
public enum VirtualMachineErrorCode 
{
    /** Unknown. */
    UNKNOWN,
    /** Not enough IP addresses. */
    NOT_ENOUGH_IP_ADDRESSES, 
    /** Not enough local controller capacity. */
    NOT_ENOUGH_LOCAL_CONTROLLER_CAPACITY, 
    /** Failed to start on local controller. */
    UNABLE_TO_START_ON_LOCAL_CONTROLLER, 
    /** Failed to update repository. */
    FAILED_TO_UPDATE_REPOSITORY, 
    /** Already running. */
    ALREADY_RUNNING, 
    /** Unable to start on group manager. */
    UNABLE_TO_START_ON_GROUP_MANAGER,
    /** Unable to collect group manager reponse. */
    UNABLE_TO_COLLECT_GROUP_MANAGER_RESPONSE,
    /** Not enough group manager capacity. */
    NOT_ENOUGH_GROUP_MANAGER_CAPACITY, 
    /** Local controller wakeup failure. */
    LOCAL_CONTROLLER_WAKEUP_FAILED
}
