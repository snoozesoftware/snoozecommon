package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalControllerList implements Serializable 
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(LocalControllerList.class);
    
     /** Serial id. */
    private static final long serialVersionUID = 1L;
    
    /** Local Controller list. */
    ArrayList<LocalControllerDescription> localControllers_;

    /**
     * Constructor.
     */
    public LocalControllerList() 
    {
        localControllers_ = new ArrayList<LocalControllerDescription>();
    }

    
    /**
     * @param localControllers
     */
    public LocalControllerList(
            ArrayList<LocalControllerDescription> localControllers) 
    {
        localControllers_ = localControllers;
    }


    /**
     * @return the localControllers
     */
    public ArrayList<LocalControllerDescription> getLocalControllers() 
    {
        return localControllers_;
    }

    /**
     * @param localControllers the localControllers to set
     */
    public void setLocalControllers(
            ArrayList<LocalControllerDescription> localControllers) 
    {
        localControllers_ = localControllers;
    }
    
    
}
