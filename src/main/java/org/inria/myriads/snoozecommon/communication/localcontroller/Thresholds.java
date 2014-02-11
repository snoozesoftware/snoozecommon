package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.util.List;

public class Thresholds implements Serializable
{

    
    /** Default id.*/
    private static final long serialVersionUID = 1L;
    
    /** Thresholds*/
    List<Double> thresholds_;

    /**
     * Thresholds.
     */
    public Thresholds()
    {
        super();
    }

    /**
     * @return the thresholds
     */
    public List<Double> getThresholds()
    {
        return thresholds_;
    }

    /**
     * @param thresholds the thresholds to set
     */
    public void setThresholds(List<Double> thresholds)
    {
        thresholds_ = thresholds;
    }

}
