package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author msimonin
 *
 */
public class HostResources implements Serializable
{
    
     /** default id.*/
    private static final long serialVersionUID = 1L;
    
    /** resources*/
    private Map<String, Resource> resources_;

    /**
     * 
     */
    public HostResources()
    {
        resources_ = new HashMap<String, Resource>();
    }

    /**
     * 
     * Copy constructor.
     * 
     * @param hostResources
     */
    public HostResources(HostResources hostResources)
    {
        resources_ = hostResources.getResources();
    }

    /**
     * @return the resources
     */
    public Map<String, Resource> getResources()
    {
        return resources_;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(Map<String, Resource> resources)
    {
        resources_ = resources;
    }
    
    
}
