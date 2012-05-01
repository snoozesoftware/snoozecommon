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
package org.inria.myriads.snoozecommon.datastructure;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/** 
 * Generic LRU cache implementation.
 * 
 * @author Eugen Feller
 * 
 * @param <K>   The key
 * @param <V>   The value
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> 
    implements Serializable
{           
    /** Serial number. */
    private static final long serialVersionUID = 1L;
    
    /** Maximum capacity. */
    private int maxCapacity_;

    /** Empty constructor. */
    public LRUCache()
    {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param maxCapacity   The maximum capacity
     */
    public LRUCache(int maxCapacity) 
    {
        super(maxCapacity + 1, 1, false);
        maxCapacity_ = maxCapacity;
    }
    
    /**
     * Determines if the oldest element schould be removed.
     * 
     * @param eldest    The eldest element
     * @return          true if removed, false otherwise
     */
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) 
    {        
        // If no capacity was specified the LRU cache is unlimited. 
        if (maxCapacity_ == 0)
        {
            return false;
        }
        
        return size() > maxCapacity_;
    }
}
