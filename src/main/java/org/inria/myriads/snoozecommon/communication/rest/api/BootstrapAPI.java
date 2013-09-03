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

import java.util.List;

import org.inria.myriads.snoozecommon.communication.groupmanager.GroupManagerDescription;
import org.inria.myriads.snoozecommon.communication.groupmanager.repository.GroupLeaderRepositoryInformation;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerDescription;
import org.inria.myriads.snoozecommon.communication.localcontroller.LocalControllerList;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.migration.ClientMigrationRequestSimple;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualClusterSubmissionRequest;
import org.inria.myriads.snoozecommon.request.HostListRequest;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Bootstrap resource interface.
 * 
 * @author Eugen Feller
 */
public interface BootstrapAPI
{
    /** 
     * gets the group leadre description to a group manager.
     * (called by the local controller)
     *  
     * @return   The group leader description
     */
    @Get("?getGroupLeaderDescription")
    GroupManagerDescription getGroupLeaderDescription();
    
    /**
     * 
     * gets the complee hierarchy from group leader to virtual machines
     * by decorating the GroupLeaderRepositoryInformation structure.
     * 
     * @return  The hierarchy in the GroupLeaderRepositoryInformation class 
     * 
     * */
    @Get("?getCompleteHierarchy")
    GroupLeaderRepositoryInformation getCompleteHierarchy();
    
    
    /**
     * Routine to destroy a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?destroyVirtualMachine")
    boolean destroyVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to suspend a virtual machine on request.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?suspendVirtualMachine")
    boolean suspendVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to reboot a virtual machine.
     * 
     * @param id   The virtual machine identifier
     * @return     true if everything ok, false otherwise
     */
    @Post("?rebootVirtualMachine")
    boolean rebootVirtualMachine(String id);
    
    /**
     * Routine to shutdown a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?shutdownVirtualMachine")
    boolean shutdownVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to resume a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?resumeVirtualMachine")
    boolean resumeVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to migrate a virtual machine.
     * 
     * @param migrationRequest   The migration request
     * @return                   true if everything ok, false otherwise
     */
    @Post("?migrateVirtualMachine")
    boolean migrateVirtualMachine(ClientMigrationRequestSimple migrationRequest);

    /**
     * Routine to start a virtual cluster.
     * 
     * @param virtualClusterDescription     The virtual cluster description.
     * @return String task identifier.
     */
    @Post("?startVirtualCluster")
    String startVirtualCluster(VirtualClusterSubmissionRequest virtualClusterDescription);

    
    
    /**
     * 
     * Gets the local controller list.
     * 
     * @return LocalController list.
     */
    @Get("?getLocalControllerList")
    LocalControllerList geLocalControllerList();
    
    /**
     * 
     * Gets the group manager list.
     * (administration purpose)
     * 
     * @param hostListRequest       host list request.
     * @return  groupmanager list
     */
    @Post("?getGroupManagerDescriptions")
    List<GroupManagerDescription> getGroupManagerDescriptions(HostListRequest hostListRequest);
    
    /**
     * 
     * Gets the local controller list.
     * (administration purpose)
     * 
     * @param hostListRequest       host list request.
     * @return local controller list.
     */
    @Post("?getLocalControllerDescriptions")
    List<LocalControllerDescription> getLocalControllerDescriptions(HostListRequest hostListRequest);
    
    /**
     * 
     * Gets the virtual machine List.
     * (administration purpose)
     * 
     * @param hostListRequest      host list request.
     * @return virtual machine list.
     */
    @Post("?getVirtualMachineDescriptions")
    List<VirtualMachineMetaData> getVirtualMachineDescriptions(HostListRequest hostListRequest);
    
}
