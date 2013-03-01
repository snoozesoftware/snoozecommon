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
package org.inria.myriads.snoozecommon.communication.groupmanager;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;

/**
 * Listen settings.
 * 
 * @author Eugen Feller
 */
public class ListenSettings    
    implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;

    /** Control data address. */
    private NetworkAddress controlDataAddress_;
    
    /** Monitoring data address. */
    private NetworkAddress monitoringDataAddress_;
    
    /** Empty constructor. */
    public ListenSettings()
    {
        controlDataAddress_ = new NetworkAddress();
        monitoringDataAddress_ = new NetworkAddress();
    }
    
    /**
     * Copy construcor.
     * 
     * @param settings  The settings
     */
    public ListenSettings(ListenSettings settings)
    {
        controlDataAddress_ = new NetworkAddress(settings.getControlDataAddress());
        monitoringDataAddress_ = new NetworkAddress(settings.getMonitoringDataAddress());
    }
    
    
    /**
     * Sets the control data address.
     * 
     * @param controlDataAddress    The control data address
     */
    public void setControlDataAddress(NetworkAddress controlDataAddress) 
    {
        controlDataAddress_ = controlDataAddress;
    }

    /**
     * Returns the control data address.
     * 
     * @return  The control data address
     */
    public NetworkAddress getControlDataAddress() 
    {
        return controlDataAddress_;
    }

    /**
     * Sets the monitring data address.
     * 
     * @param monitoringDataAddress     The monitoring data address
     */
    public void setMonitoringDataAddress(NetworkAddress monitoringDataAddress) 
    {
        monitoringDataAddress_ = monitoringDataAddress;
    }

    /**
     * Returns the monutoring data address.
     * 
     * @return  The monitoring data address
     */
    public NetworkAddress getMonitoringDataAddress() 
    {
        return monitoringDataAddress_;
    }    
}
