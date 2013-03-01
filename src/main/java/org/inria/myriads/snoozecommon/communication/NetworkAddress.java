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
package org.inria.myriads.snoozecommon.communication;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.globals.Globals;

/**
 * Network address.
 * 
 * @author Eugen Feller
 */
public final class NetworkAddress
    implements Serializable
{    
    /** Default version. */
    private static final long serialVersionUID = 1L;
    
    /** The address. */
    private String address_;
    
    /** The port. */
    private int port_;
    
    /** Constructor. */
    public NetworkAddress()
    {
        address_ = Globals.DEFAULT_INITIALIZATION;
        port_ = -1;
    } 

    /** 
     * Copy constructor.
     * 
     * @param originalAddress   The original address
     */ 
    public NetworkAddress(NetworkAddress originalAddress)
    {
        address_ = originalAddress.getAddress();
        port_ = originalAddress.getPort();
    }
    
    /**
     * Returns the address.
     * 
     * @return      The address
     */
    public String getAddress()
    {
        return address_;
    }
    
    /**
     * Returns the port.
     * 
     * @return      The port
     */
    public int getPort()
    {
        return port_;
    }
    
    /**
     * Sets the port.
     * 
     * @param port  The port
     */
    public void setPort(int port) 
    {
        port_ = port;
    }

    /**
     * Sets the address.
     * 
     * @param address     The address
     */
    public void setAddress(String address) 
    {
        address_ = address;
    }
    
    /**
     * Converts to string.
     * 
     * @return  The string representation
     */
    @Override
    public String toString()
    {
        String output = address_ + ":" + String.valueOf(port_);
        return output;
    }
}
