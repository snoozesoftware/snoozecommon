package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * LocalControllerList.
 * 
 * @author msimonin
 *
 */
public class LocalControllerList implements Serializable
{
    /** Define the logger. */
    private static final Logger log_ = LoggerFactory.getLogger(LocalControllerList.class);
    
     /** Serial id. */
    private static final long serialVersionUID = 1L;
    
    /** Local Controller list. */
    private ArrayList<LocalControllerDescription> localControllers_;

    /**
     * Constructor.
     */
    public LocalControllerList() 
    {
        localControllers_ = new ArrayList<LocalControllerDescription>();
    }

    
    /**
     * 
     * Constructor.
     * 
     * @param localControllers      the localcontrollers.
     */
    public LocalControllerList(
            ArrayList<LocalControllerDescription> localControllers)
    {
        localControllers_ = localControllers;
    }


    /**
     * 
     *  "Copy" constructor.
     * 
     * @param localControllers      localcontrollers.
     */
    public LocalControllerList(
            HashMap<String, LocalControllerDescription> localControllers) 
    {
        localControllers_ = new ArrayList<LocalControllerDescription>();
        
        for (LocalControllerDescription localController : localControllers.values())
        {
            localControllers_.add(localController);
        }
        
        
    }


    /**
     * 
     * Gets the local controllers.
     * 
     * @return the localControllers
     */
    public ArrayList<LocalControllerDescription> getLocalControllers() 
    {
        return localControllers_;
    }

    /**
     * 
     * Sets the local controllers.
     * 
     * @param localControllers the localControllers to set
     */
    public void setLocalControllers(
            ArrayList<LocalControllerDescription> localControllers) 
    {
        localControllers_ = localControllers;
    }
    
    
}
