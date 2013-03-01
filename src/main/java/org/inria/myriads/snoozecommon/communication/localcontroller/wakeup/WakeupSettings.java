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
package org.inria.myriads.snoozecommon.communication.localcontroller.wakeup;

import java.io.Serializable;

/** 
 * Wakeup driver.
 * 
 * @author Eugen Feller
 */
public class WakeupSettings 
    implements Serializable
{
    /** Default version. */
    private static final long serialVersionUID = 1L;
    
    /** Driver. */
    private WakeupDriver driver_;

    /** Options. */
    private String options_;
    
    /** Empty. */
    public WakeupSettings()
    {
    }
    
    /**
     * Wakeup driver.
     * 
     * @param settings    The wakeup settings
     */
    public WakeupSettings(WakeupSettings settings)
    {
        driver_ = settings.getDriver();
        options_ = settings.getOptions();
    } 
    
    /**
     * Sets the driver.
     * 
     * @param driver    The wakeup driver
     */
    public void setDriver(WakeupDriver driver)
    {
        driver_ = driver;
    }
    
    /**
     * Returns the wakeup driver.
     * 
     * @return  The wakeup driver
     */
    public WakeupDriver getDriver()
    {
        return driver_;
    }
    
    /**
     * Sets the options.
     * 
     * @param options   The options
     */
    public void setOptions(String options) 
    {
        options_ = options;
    }

    /**
     * Returns the options.
     * 
     * @return  The options
     */
    public String getOptions() 
    {
        return options_;
    }
}
