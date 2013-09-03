package org.inria.myriads.snoozecommon.communication.virtualcluster.migration;

import java.io.Serializable;

/**
 * 
 * Client Migration Request.
 * 
 * @author msimonin
 *
 */
public class ClientMigrationRequestSimple implements Serializable
{
    /** Default serial. */
    private static final long serialVersionUID = 1L;
   
    /** Virtual machine id. */
    private String virtualMachineId_;
    
    /** LocalController id.*/
    private String localControllerId_;

    /**
     * @return the virtualMachineId
     */
    public String getVirtualMachineId() 
    {
        return virtualMachineId_;
    }

    /**
     * @param virtualMachineId the virtualMachineId to set
     */
    public void setVirtualMachineId(String virtualMachineId) 
    {
        this.virtualMachineId_ = virtualMachineId;
    }

    /**
     * @return the localControllerId
     */
    public String getLocalControllerId() 
    {
        return localControllerId_;
    }

    /**
     * @param localControllerId the localControllerId to set
     */
    public void setLocalControllerId(String localControllerId) 
    {
        this.localControllerId_ = localControllerId;
    }
    
    

}
