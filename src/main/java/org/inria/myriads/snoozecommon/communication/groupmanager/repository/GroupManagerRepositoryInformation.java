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
package org.inria.myriads.snoozecommon.communication.groupmanager.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;

/**
 * Group manager repository information.
 * 
 * @author Eugen Feller
 */
public final class GroupManagerRepositoryInformation 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
    
    /** Descriptions of the local controllers. */
    private ArrayList<LocalControllerDescription> localControllers_;
       
    /**
     * Sets the local controller descriptions.
     * 
     * @param localControllers      The local controller descriptions
     */
    public void setLocalControllerDescriptions(ArrayList<LocalControllerDescription> localControllers) 
    {
        localControllers_ = localControllers;
    }
    
    /**
     * Returns the local controller descriptions.
     * 
     * @return  The local controller descriptions
     */
    public ArrayList<LocalControllerDescription> getLocalControllerDescriptions() 
    {
        return localControllers_;
    }
}
