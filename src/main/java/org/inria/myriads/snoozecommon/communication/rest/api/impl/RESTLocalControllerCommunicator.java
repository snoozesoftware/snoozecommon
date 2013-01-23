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
package org.inria.myriads.snoozecommon.communication.rest.api.impl;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.communication.NodeRole;
import org.inria.myriads.snoozecommon.communication.rest.api.LocalControllerAPI;
import org.inria.myriads.snoozecommon.communication.rest.util.RESTUtil;
import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;
import org.inria.myriads.snoozecommon.communication.virtualcluster.migration.MigrationRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionRequest;
import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineSubmissionResponse;
import org.inria.myriads.snoozecommon.guard.Guard;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST local controller communicator.
 * 
 * @author Eugen Feller
 */
public final class RESTLocalControllerCommunicator 
    implements LocalControllerAPI
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(RESTLocalControllerCommunicator.class);
    
    /** Local controller description. */
    private NetworkAddress localControllerAddress_;
    
    /**
     * Constructor.
     * 
     * @param localControllerAddress    The local controller address
     */
    public RESTLocalControllerCommunicator(NetworkAddress localControllerAddress) 
    {
        Guard.check(localControllerAddress);
        log_.debug("Initializing REST local controller communicator");     
        localControllerAddress_ = localControllerAddress;
    }

    /**
     * Creates a client resource.
     * 
     * @return  The client resource
     */
    private ClientResource createClientResource()
    {
        log_.debug("Creating client resource");
        ClientResource clientResource = RESTUtil.createClientResource(NodeRole.localcontroller, 
                                                                       localControllerAddress_);
        return clientResource;
    }
    
    /**
     * Starts a virtual machine.
     * 
     * @param submissionRequest  The virtual machine submission request 
     * @return                   true if everything ok, else otherwise
     */
    @Override
    public VirtualMachineSubmissionResponse startVirtualMachines(
            VirtualMachineSubmissionRequest submissionRequest)
    {
        Guard.check(submissionRequest);
        log_.debug(String.format("Starting %s virtual machine on local controller", 
                   submissionRequest.getVirtualMachineMetaData().size()));
        
        ClientResource clientResource = null;
        VirtualMachineSubmissionResponse submissionResponse = null;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            submissionResponse = localControllerResource.startVirtualMachines(submissionRequest);
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine start", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return submissionResponse;
    }

    /**
     * Destroy a virtual machine.
     * 
     * @param virtualMachineId  The virtual machine identifier 
     * @return                  true if everything ok, else otherwise
     */
    @Override
    public boolean destroyVirtualMachine(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Destroing virtual machine %s on local controller", virtualMachineId));
        
        ClientResource clientResource = null;
        boolean isDestroyed = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isDestroyed = localControllerResource.destroyVirtualMachine(virtualMachineId);  
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine destroy", exception);
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
     * Resume a virtual machine.
     * 
     * @param id  The virtual machine identifier 
     * @return    true if everything ok, else otherwise
     */
    @Override
    public boolean resumeVirtualMachine(String id)
    {
        Guard.check(id);
        log_.debug(String.format("Resuming virtual machine %s on local controller", id));
        
        ClientResource clientResource = null;
        boolean isResumed = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isResumed = localControllerResource.resumeVirtualMachine(id);   
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine resume", exception);
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
     * Suspend a virtual machine on request.
     * 
     * @param virtualMachineId  The virtual machine identifier 
     * @return                  true if everything ok, else otherwise
     */
    @Override
    public boolean suspendVirtualMachineOnRequest(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Suspending virtual machine %s on request", virtualMachineId));
        
        ClientResource clientResource = null;
        boolean isSuspended = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isSuspended = localControllerResource.suspendVirtualMachineOnRequest(virtualMachineId);   
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine suspend", exception);
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
     * Suspend a virtual machine.
     * 
     * @param virtualMachineId  The virtual machine identifier 
     * @return                  true if everything ok, else otherwise
     */
    @Override
    public boolean suspendVirtualMachineOnMigration(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Suspending virtual machine %s on migration", virtualMachineId));
        
        ClientResource clientResource = null;
        boolean isSuspended = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isSuspended = localControllerResource.suspendVirtualMachineOnMigration(virtualMachineId);   
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine suspend on migration", exception);
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
     * Shutdown a virtual machine.
     * 
     * @param virtualMachineId  The virtual machine identifier 
     * @return                  true if everything ok, else otherwise
     */
    @Override
    public boolean shutdownVirtualMachine(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Shutdown virtual machine %s on local controller", virtualMachineId));
           
        ClientResource clientResource = null;
        boolean isShutdown = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isShutdown = localControllerResource.shutdownVirtualMachine(virtualMachineId);    
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine shutdown", exception);
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
     * Reboot a virtual machine.
     * 
     * @param virtualMachineId  The virtual machine identifier 
     * @return                  true if everything ok, else otherwise
     */
    @Override
    public boolean rebootVirtualMachine(String virtualMachineId)
    {
        Guard.check(virtualMachineId);
        log_.debug(String.format("Reboot virtual machine %s on local controller", virtualMachineId));
           
        ClientResource clientResource = null;
        boolean isRebooted = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isRebooted = localControllerResource.rebootVirtualMachine(virtualMachineId);    
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine reboot", exception);
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
     * Send suspend to disk request.
     * 
     * @return    true if everything ok, else otherwise
     */
    @Override
    public boolean suspendNodeToDisk() 
    {
        log_.debug(String.format("Sending suspend to disk request to local controller"));
        
        ClientResource clientResource = null;
        boolean isSuspended;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isSuspended = localControllerResource.suspendNodeToDisk();
        } 
        catch (Exception exception)
        {
            log_.debug(String.format("Error during suspend: %s! Not that bad here at all!", exception.getMessage()));
            return true;
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
     * Send suspend to ram request.
     * 
     * @return    true if everything ok, else otherwise
     */
    @Override
    public boolean suspendNodeToRam() 
    {
        log_.debug(String.format("Sending suspend to ram request to local controller"));
        
        ClientResource clientResource = null;
        boolean isSuspended;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isSuspended = localControllerResource.suspendNodeToRam();     
        } 
        catch (Exception exception)
        {
            log_.debug(String.format("Error during suspend: %s! Not that bad here at all!", exception.getMessage()));
            return true;
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
     * Send suspend to both request.
     * 
     * @return    true if everything ok, else otherwise
     */
    @Override
    public boolean suspendNodeToBoth() 
    {
        log_.debug(String.format("Sending suspend to both request to local controller"));
        
        ClientResource clientResource = null;
        boolean isSuspended;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isSuspended = localControllerResource.suspendNodeToBoth();   
        } 
        catch (Exception exception)
        {
            log_.debug(String.format("Error during suspend: %s! Not that bad here at all!", exception.getMessage()));
            return true;
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
     * Sending shutdown command to host.
     * 
     * @return    true if everything ok, else otherwise
     */
    @Override
    public boolean shutdownNode() 
    {
        log_.debug(String.format("Sending shutdown command to local controller"));
        
        ClientResource clientResource = null;
        boolean isShutdown;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isShutdown = localControllerResource.shutdownNode();
        } 
        catch (Exception exception)
        {
            log_.debug(String.format("Error during shutdown: %s! Not that bad here at all!", exception.getMessage()));
            return true;
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
     * Migrates a virtual machine.
     * 
     * @param migrationRequest      The migration request
     * @return                      true if everything ok, false otherwise
     */
    @Override
    public boolean migrateVirtualMachine(MigrationRequest migrationRequest) 
    {
        Guard.check(migrationRequest);
        String destinationAddress = 
            migrationRequest.getDestinationVirtualMachineLocation().getLocalControllerControlDataAddress().getAddress();
        int destinationPort = 
            migrationRequest.getDestinationVirtualMachineLocation().getLocalControllerControlDataAddress().getPort();
        log_.debug(String.format("Starting virtual machine %s migration to: %s:%d and hypervisor port: %d",
                                 migrationRequest.getSourceVirtualMachineLocation().getVirtualMachineId(),
                                 destinationAddress,
                                 destinationPort,
                                 migrationRequest.getDestinationHypervisorSettings().getPort()));
        
        ClientResource clientResource = null;
        boolean isMigrated = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isMigrated = localControllerResource.migrateVirtualMachine(migrationRequest);
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine migration", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        return isMigrated;   
    }

    /**
     * Starts virtual machine monitoring.
     * 
     * @param virtualMachineMetaData     The virtual machine meta data
     * @return                           true if everything ok, false otherwise
     */
    @Override
    public boolean startVirtualMachineMonitoring(VirtualMachineMetaData virtualMachineMetaData) 
    {
        Guard.check(virtualMachineMetaData);
        log_.debug("Sending virtual machine monitoring start request to local controller");
        
        ClientResource clientResource = null;
        boolean isStarted = false;
        try
        {
            clientResource = createClientResource();
            LocalControllerAPI localControllerResource = clientResource.wrap(LocalControllerAPI.class); 
            isStarted = localControllerResource.startVirtualMachineMonitoring(virtualMachineMetaData);
        } 
        catch (Exception exception)
        {
            log_.debug("Error during virtual machine monitoring start", exception);
        }
        finally
        {
            if (clientResource != null)
            {
                clientResource.release();
            }
        }
        
        return isStarted;
    }


}
