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
package org.inria.myriads.snoozecommon.communication.localcontroller.hypervisor;

import java.io.Serializable;

/**
 * Migration parameters.
 * 
 * @author Eugen Feller
 */
public class MigrationSettings 
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;

    /** Migration merhod. */
    private MigrationMethod method_;
    
    /** Convergence threshold. */
    private int timeout_;
    
    /** Empty constructor. */
    public MigrationSettings()
    {   
    }
    
    /** 
     * Copy constructor.
     * 
     * @param migration The migration parameters
     */
    public MigrationSettings(MigrationSettings migration) 
    {
        method_ = migration.getMethod();
        timeout_ = migration.getTimeout();
    }

    /**
     * Sets the migration method.
     * 
     * @param method   The migration method
     */
    public void setMethod(MigrationMethod method) 
    {
        method_ = method;
    }

    /**
     * Returns the migration method.
     * 
     * @return  The migration method
     */
    public MigrationMethod getMethod() 
    {
        return method_;
    }
    
    /**
     * Migration convergence timeout.
     * 
     * @param timeout      The migration convergence timeout
     */
    public void setTimeout(int timeout) 
    {
        timeout_ = timeout;
    }

    /**
     * Returns the migration convergence timeout.
     * 
     * @return      The migration convergence timeout
     */
    public int getTimeout() 
    {
        return timeout_;
    }
}
