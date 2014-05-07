package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;

import org.inria.myriads.snoozecommon.communication.NetworkAddress;
import org.inria.myriads.snoozecommon.globals.Globals;

/**
 * 
 * Local Controller Location.
 * 
 * @author msimonin
 *
 */
public class LocalControllerLocation implements Serializable 
{
    /** Default version. */
    private static final long serialVersionUID = 1L;
    
    /** Local controller id. */
    private String localControllerId_;
    
    /** local controller group manager identifier. */
    private String groupManagerId_;
    
    /** Group manager address. */
    private NetworkAddress groupManagerControlDataAddress_;

    /**
     * 
     * Constructor.
     * 
     */
    public LocalControllerLocation() 
    {
        localControllerId_ = Globals.DEFAULT_INITIALIZATION;
        groupManagerId_ = Globals.DEFAULT_INITIALIZATION;
        groupManagerControlDataAddress_ = new NetworkAddress();
    }

    /**
     * 
     * copy constructor.
     * 
     * @param   original    Original to copy.
     * 
     */
    public LocalControllerLocation(LocalControllerLocation original)
    {
        localControllerId_ = original.getLocalControllerId();
        groupManagerId_ = original.getGroupManagerId();
        groupManagerControlDataAddress_ = original.getGroupManagerControlDataAddress();
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
        localControllerId_ = localControllerId;
    }

    /**
     * @return the groupManagerId
     */
    public String getGroupManagerId() 
    {
        return groupManagerId_;
    }

    /**
     * @param groupManagerId the groupManagerId to set
     */
    public void setGroupManagerId(String groupManagerId) 
    {
        groupManagerId_ = groupManagerId;
    }

    /**
     * @return the groupManagerControlDataAddress
     */
    public NetworkAddress getGroupManagerControlDataAddress() 
    {
        return groupManagerControlDataAddress_;
    }

    /**
     * @param groupManagerControlDataAddress the groupManagerControlDataAddress to set
     */
    public void setGroupManagerControlDataAddress(NetworkAddress groupManagerControlDataAddress) 
    {
        groupManagerControlDataAddress_ = groupManagerControlDataAddress;
    }
    
}
