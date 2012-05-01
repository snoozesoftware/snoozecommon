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
package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;

/**
 * Local controller assignment.
 * 
 * @author Eugen Feller
 */
public final class AssignedGroupManager
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;

    /** Previous identifier. */
    private String localControllerId_;
    
    /** Previous group manager. */
    private GroupManagerDescription groupManager_;
    
    /**
     * Sets the local controller identifier.
     * 
     * @param localControllerId     The local controller identifier
     */
    public void setLocalControllerId(String localControllerId) 
    {
        localControllerId_ = localControllerId;
    }

    /**
     * Returns the local controller identifier.
     * 
     * @return  The local controller identifier
     */
    public String getLocalControllerId() 
    {
        return localControllerId_;
    }

    /**
     * Sets the group manager description.
     * 
     * @param groupManager   The  group manager description
     */
    public void setGroupManager(GroupManagerDescription groupManager) 
    {
        groupManager_ = groupManager;
    }

    /**
     * Returns the group manager description.
     * 
     * @return  The group manager description
     */
    public GroupManagerDescription getGroupManager() 
    {
        return groupManager_;
    }

}
