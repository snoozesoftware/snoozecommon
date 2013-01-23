package org.inria.myriads.snoozecommon.communication.virtualmachine;

import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * 
 * Client Migration Request.
 * 
 * @author msimonin
 *
 */
public class ClientMigrationRequest 
{

    /** The old loclation.*/
    private VirtualMachineLocation oldLocation_;
    
    /** The new loclation.*/
    private VirtualMachineLocation newLocation_;

    /**
     * 
     * Empty Constructor.
     * 
     * @param oldLocation
     * @param newLocation
     */
    public ClientMigrationRequest() 
    {
        
    }
    
    
    /**
     * 
     * Constructor.
     * 
     * @param oldLocation           The old location 
     * @param newLocation           The new location
     */
    public ClientMigrationRequest(VirtualMachineLocation oldLocation,
            VirtualMachineLocation newLocation) 
    {
        oldLocation_ = oldLocation;
        newLocation_ = newLocation;
    }


    /**
     * @return the oldLocation
     */
    public VirtualMachineLocation getOldLocation() 
    {
        return oldLocation_;
    }


    /**
     * @param oldLocation the oldLocation to set
     */
    public void setOldLocation(VirtualMachineLocation oldLocation) 
    {
        oldLocation_ = oldLocation;
    }


    /**
     * @return the newLocation
     */
    public VirtualMachineLocation getNewLocation() 
    {
        return newLocation_;
    }


    /**
     * @param newLocation the newLocation to set
     */
    public void setNewLocation(VirtualMachineLocation newLocation)
    {
        newLocation_ = newLocation;
    }
    
    
    
}
