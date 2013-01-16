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
package org.inria.myriads.snoozecommon.communication.rest.api;

import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.migration.MigrationRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionResponse;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Local controller resource interface.
 * 
 * @author Eugen Feller
 */
public interface LocalControllerAPI
{    
    /** 
     * Start a virtual machine.
     * (called by the group manager)
     *  
     * @param submissionRequest     The submission request
     * @return                      The submission response
     */
    @Post("?startVirtualMachines")
    VirtualMachineSubmissionResponse startVirtualMachines(VirtualMachineSubmissionRequest submissionRequest);
    
    /**
     * Routine to suspend a virtual machine on request.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?suspendVirtualMachineOnRequest")
    boolean suspendVirtualMachineOnRequest(String virtualMachineId);
    
    /**
     * Routine to resume a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?resumeVirtualMachine")
    boolean resumeVirtualMachine(String virtualMachineId);
        
    /**
     * Routine to shutdown a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?shutdownVirtualMachine")
    boolean shutdownVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to destroy a virtual machine.
     * 
     * @param virtualMachineId   The virtual machine identifier
     * @return                   true if everything ok, false otherwise
     */
    @Post("?destroyVirtualMachine")
    boolean destroyVirtualMachine(String virtualMachineId);
    
    /**
     * Routine to migrate a virtual machine.
     * 
     * @param migrationRequest   The migration request
     * @return                   true if everything ok, false otherwise
     */
    @Post("?migrateVirtualMachine")
    boolean migrateVirtualMachine(MigrationRequest migrationRequest);
        
    /**
     * Routine to suspend the local controller to ram.
     * 
     * @return  true if everything ok, false otherwise
     */
    @Get("?suspendNodeToRam")
    boolean suspendNodeToRam();
    
    /**
     * Routine to suspend the local controller to disk.
     * 
     * @return  true if everything ok, false otherwise
     */
    @Get("?suspendNodeToBoth")
    boolean suspendNodeToDisk();
    
    /**
     * Routine to suspend the local controller to disk.
     * 
     * @return  true if everything ok, false otherwise
     */
    @Get("?suspendNodeToBoth")
    boolean suspendNodeToBoth();
    
    /**
     * Routine to shutdown the host.
     * 
     * @return  true if everything ok, false otherwise
     */
    @Get("?shutdownNode")
    boolean shutdownNode();

    /**
     * Starts virtual machine montitoring.
     * 
     * @param virtualMachineMetaData        The virtual machine meta data
     * @return                              true if everything ok, false otherwise
     */
    @Post("?startVirtualMachineMonitoring")
    boolean startVirtualMachineMonitoring(VirtualMachineMetaData virtualMachineMetaData);

    /**
     * Suspends a virtual machine on migration.
     * 
     * @param virtualMachineId      The virtual machine identifier
     * @return                      true if everything ok, false otherwise
     */
    @Post("?suspendVirtualMachineOnMigration")
    boolean suspendVirtualMachineOnMigration(String virtualMachineId);
}
