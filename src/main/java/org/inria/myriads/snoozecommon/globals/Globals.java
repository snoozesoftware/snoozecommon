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
package org.inria.myriads.snoozecommon.globals;

/**
 * Global variables.
 * 
 * @author Eugen Feller
 */
public final class Globals 
{
    /** Default value. */
    public static final String DEFAULT_INITIALIZATION = "UNKNOWN";
        
    /** Cpu utilization index. */
    public static final int CPU_UTILIZATION_INDEX = 0;
    
    /** Memory utilization index. */
    public static final int MEMORY_UTILIZATION_INDEX = 1;
    
    /** Network rx utilization index. */
    public static final int NETWORK_RX_UTILIZATION_INDEX = 2;
    
    /** Network tx utilization index. */
    public static final int NETWORK_TX_UTILIZATION_INDEX = 3;

    /** Default CPU demand (for resize).*/
    public static final double DEFAULT_CPU = 1;
    
    /** Default Memory demand (for resize)*/
    public static final double DEFAULT_MEMORY = 512000;
    
    
    /** Hide constructor. */
    private Globals()
    {
        throw new UnsupportedOperationException();
    }
}
