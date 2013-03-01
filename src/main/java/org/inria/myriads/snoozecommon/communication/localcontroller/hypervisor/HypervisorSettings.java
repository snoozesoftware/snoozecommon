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
package org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor;

import java.io.Serializable;

/**
 * Hypervisor settings.
 * 
 * @author Eugen Feller
 */
public final class HypervisorSettings  
    implements Serializable
{
    /** Default version. */
    private static final long serialVersionUID = 1L;
    
    /** Hypervisor port. */
    private int port_;
    
    /** Hypervisor driver. */
    private HypervisorDriver driver_;

    /** Hypervisor transport. */
    private HypervisorTransport transport_;
    
    /** Migration parameters. */
    private MigrationSettings migration_;
    
    /** Empty constructor. */
    public HypervisorSettings() 
    {
        migration_ = new MigrationSettings();
    }
    
    /** 
     * Copy constructor. 
     * 
     * @param hypervisorSettings    The original object
     */
    public HypervisorSettings(HypervisorSettings hypervisorSettings) 
    {
        driver_ = hypervisorSettings.getDriver();
        transport_ = hypervisorSettings.getTransport();
        port_ = hypervisorSettings.getPort();
        migration_ = new MigrationSettings(hypervisorSettings.getMigration());
    }

    /**
     * Sets the hypervisor driver.
     * 
     * @param driver  The hypervisor driver
     */
    public void setDriver(HypervisorDriver driver) 
    {
        driver_ = driver;
    }

    /**
     * Returns the hypervisor driver.
     * 
     * @return  The hypervisor driver
     */
    public HypervisorDriver getDriver() 
    {
        return driver_;
    }

    /**
     * Sets the hypervisor port.
     * 
     * @param port    The hypervisor port
     */
    public void setPort(int port) 
    {
        port_ = port;
    }

    /**
     * Returns the hypervisor port.
     * 
     * @return  The hypervisor port
     */
    public int getPort() 
    {
        return port_;
    }
    
    /**
     * Sets the hypervisor transport.
     * 
     * @param transport   The hypervisor transport
     */
    public void setTransport(HypervisorTransport transport) 
    {
        transport_ = transport;
    }

    /**
     * Returns the hypervisor transport.
     * 
     * @return  The hypervisor transport
     */
    public HypervisorTransport getTransport() 
    {
        return transport_;
    }

    /**
     * Sets the migration parameters.
     * 
     * @param migration  The migration parameters
     */
    public void setMigration(MigrationSettings migration) 
    {
        migration_ = migration;
    }

    /**
     * Returns the migration parameters.
     * 
     * @return  The migration parameters
     */
    public MigrationSettings getMigration() 
    {
        return migration_;
    }
}
