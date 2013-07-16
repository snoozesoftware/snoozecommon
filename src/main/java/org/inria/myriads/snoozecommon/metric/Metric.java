package org.inria.myriads.snoozecommon.metric;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author msimonin
 *
 */
public class Metric implements Serializable
{
    
    
    /** Serialization id. */
    private static final long serialVersionUID = 1L;
    
    /** Metric Type. */
    private String name_;
    
    /** Timestamp. */
    private Long timestamp_ ; 
    
    /** Value. */
    private Double value_;
 
    
    
    public Metric()
    {
        timestamp_ = new Timestamp(System.currentTimeMillis()).getTime(); 
    }
    /**
     * 
     * Constructor.
     * 
     * @param type
     * @param timestamp
     * @param value
     */
    public Metric(String type, Double value)
    {
        name_ = type;
        timestamp_ = new Timestamp(System.currentTimeMillis()).getTime();
        value_ = value;
    }
    
    /**
     * 
     * Constructor.
     * 
     * @param type
     * @param timestamp
     * @param value
     */
    public Metric(String type, Long timestamp, Double value)
    {
        name_ = type;
        timestamp_ = timestamp;
        value_ = value;
    }
    
    /**
     * @return the type
     */
    public String getName()
    {
        return name_;
    }

    /**
     * @param type the type to set
     */
    public void setName(String name)
    {
        name_ = name;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp()
    {
        return timestamp_;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Long timestamp)
    {
        timestamp_ = timestamp;
    }

    /**
     * @return the value
     */
    public Double getValue()
    {
        return value_;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value)
    {
        value_ = value;
    }
    
    
    

}
