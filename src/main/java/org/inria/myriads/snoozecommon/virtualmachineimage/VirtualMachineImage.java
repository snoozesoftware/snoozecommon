package org.inria.myriads.snoozecommon.virtualmachineimage;

import org.inria.myriads.snoozecommon.globals.Globals;

/**
 * 
 * Single image representation.
 * 
 * @author msimonin
 *
 */
public class VirtualMachineImage 
{
    /** The name.*/
    private String name_;
    
    /** The path. */
    private String path_;
    
    /** The format. */
    private String format_;
    
    /** The capacity size (bytes).*/
    private long capacity_;

    /** The allocation (virtual in bytes) size.*/
    private long allocation_;
    
    /** The backing store (optionnal).*/
    private String backingStore_;
    
    /**
     * 
     * Constructor.
     * 
     */
    public VirtualMachineImage() 
    {
        // default values
        name_ = Globals.DEFAULT_INITIALIZATION;
        path_ = Globals.DEFAULT_INITIALIZATION;
        format_ = Globals.DEFAULT_DISK_FORMAT;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name_;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        name_ = name;
    }

    /**
     * 
     * Gets the path.
     * 
     * @return the path_
     */
    public String getPath() 
    {
        return path_;
    }

    /**
     * sets the path.
     * 
     * @param path the path to set
     */
    public void setPath(String path)
    {
        path_ = path;
    }

    /**
     * @return the format
     */
    public String getFormat()
    {
        return format_;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format)
    {
        format_ = format;
    }

    /**
     * @return the capacity
     */
    public long getCapacity() 
    {
        return capacity_;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(long capacity) 
    {
        capacity_ = capacity;
    }

    /**
     * @return the allocation
     */
    public long getAllocation() 
    {
        return allocation_;
    }

    /**
     * @param allocation the allocation to set
     */
    public void setAllocation(long allocation) 
    {
        allocation_ = allocation;
    }

    /**
     * 
     * Sets the backing store.
     * 
     * @param sourcePath        The path to the backing store.
     */
    public void setBackingStore(String sourcePath)
    {
        backingStore_ = sourcePath;
    }
    
    /**
     * 
     * Gets the backing store.
     * @return the backing store.
     */
    public String getBackingStore()
    {
        return backingStore_;
    }
    
}
