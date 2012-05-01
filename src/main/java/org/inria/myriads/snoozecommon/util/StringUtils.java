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

import java.util.ArrayList;
import java.util.List;

import org.inria.myriads.snoozecommon.guard.Guard;

/**
 * String utils.
 * 
 * @author Eugen Feller
 */
public final class StringUtils 
{
    /** Hide constructor. */
    private StringUtils()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Can be used to convert a string to inger array.
     *   
     * @param line             The content to convert
     * @param separator        The separator
     * @return                 The integer list
     */
    public static List<Integer> convertStringToIntegerArray(String line, String separator) 
    {
        Guard.check(line, separator);
        ArrayList<Integer> result = new ArrayList<Integer>();  
        String[] values = line.split(separator);
    
        for (int i = 0; i < values.length; i++) 
        {
            Integer number = Integer.valueOf(values[i]);
            result.add(number);
        }
    
        return result;
    }
 
    /**
     * Can be used to convert a string to inger array.
     *   
     * @param line             The content to convert
     * @param separator        The separator
     * @return                 The integer list
     */
    public static List<Double> convertStringToDoubleArray(String line, String separator) 
    {
        Guard.check(line, separator);
        ArrayList<Double> result = new ArrayList<Double>();  
        String[] values = line.split(separator);
    
        for (int i = 0; i < values.length; i++) 
        {
            Double number = Double.valueOf(values[i]);
            result.add(number);
        }
    
        return result;
    }
    
    /**
     * Can be used to convert a string to inger array.
     *   
     * @param line             The content to convert
     * @param separator        The separator
     * @return                 The integer list
     */
    public static List<String> convertStringToStringArray(String line, String separator) 
    {
        Guard.check(line, separator);
        ArrayList<String> result = new ArrayList<String>();  
        String[] values = line.split(separator);
    
        for (int i = 0; i < values.length; i++) 
        {
            String number = values[i];
            result.add(number);
        }
    
        return result;
    }
}
