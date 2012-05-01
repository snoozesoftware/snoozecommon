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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Error utils.
 * 
 * @author Eugen Feller
 *
 */
public final class ErrorUtils 
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(NetworkUtils.class);
    
    /** Hide constructor. */
    private ErrorUtils()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Terminates the service.
     * 
     * @param errorMessage     The exception
     */
    public static void processError(String errorMessage)
    {
        log_.error(errorMessage);
        System.exit(1);
    }
    
    /**
     * Returns the string representation of a stacktrace.
     * 
     * @param exception     The exception
     * @return              The string
     */
    public static String getStackTrace(Exception exception) 
    {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        exception.printStackTrace(printWriter);
        return result.toString();
    }
}
