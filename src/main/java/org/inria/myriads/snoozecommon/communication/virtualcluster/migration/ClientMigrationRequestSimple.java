package org.inria.myriads.snoozecommon.communication.virtualcluster.migration;

import java.io.Serializable;

/**
 * 
 * Client Migration Request
 * 
 * @author msimonin
 *
 */
public class ClientMigrationRequestSimple implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
   
    /** Virtual machine id.*/
    private String virtualMachineId;
    
    /** LocalController id.*/
    private String localControllerId;

    /**
     * @return the virtualMachineId
     */
    public String getVirtualMachineId() 
    {
        return virtualMachineId;
    }

    /**
     * @param virtualMachineId the virtualMachineId to set
     */
    public void setVirtualMachineId(String virtualMachineId) 
    {
        this.virtualMachineId = virtualMachineId;
    }

    /**
     * @return the localControllerId
     */
    public String getLocalControllerId() 
    {
        return localControllerId;
    }

    /**
     * @param localControllerId the localControllerId to set
     */
    public void setLocalControllerId(String localControllerId) 
    {
        this.localControllerId = localControllerId;
    }
    
    

}
