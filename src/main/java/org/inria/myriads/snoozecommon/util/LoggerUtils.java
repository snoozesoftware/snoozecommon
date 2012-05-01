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
package org.inria.myriads.snoozecommon.util;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger utils.
 * 
 * @author Eugen Feller
 */
public final class LoggerUtils 
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(LoggerUtils.class);
    
    /** Hide constructor. */
    private LoggerUtils()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Configure log4j.
     * 
     * @param logFile   The log file
     */
    public static void configureLogger(String logFile)
    {
        System.getProperties().put("org.restlet.engine.loggerFacadeClass", 
                                   "org.restlet.ext.slf4j.Slf4jLoggerFacade");
        
        File file = new File(logFile);
        if (file.exists() && file.canRead()) 
        {   
            DOMConfigurator.configure(logFile);
        } else
        {
            System.out.println("Log file " + logFile + " does not exist or is not readable! Falling back to default!");
            BasicConfigurator.configure();
        }
        
        log_.debug("Logger configuration finished");
    }
}
