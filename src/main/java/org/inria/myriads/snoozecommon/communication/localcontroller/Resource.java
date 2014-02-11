package org.inria.myriads.snoozecommon.communication.localcontroller;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import org.inria.myriads.snoozecommon.datastructure.LRUCache;

/**
 * @author msimonin
 *
 */
public class Resource implements Serializable
{

    /** Default id.*/
    private static final long serialVersionUID = 1L;
    
    /** Resource name (cpu_user, mem_free). */
    private String name_;
    
    /** Total. */
    private double total_;
    
    /** Thresholds. */
    private List<Double> thresholds_;
    
    /** History.*/
    private LRUCache <Long, Double> history_;
    
    /** historySize.*/
    private int historySize_;

    /**
     * 
     * Constructor.
     * 
     */
    public Resource(int historySize)
    {
        super();
        history_ = new LRUCache<Long, Double>(historySize);
        historySize_ = historySize;
    }
    
    /**
     * 
     * Constructor.
     * 
     */
    public Resource()
    {
        super();
        history_ = new LRUCache<Long, Double>();
    }

    /**
     * Copy constructor.
     * 
     */
    public Resource(Resource resource, long pastTimestamp)
    {
        //copyResource.setHistorySize(resource.getHistorySize());
        LRUCache<Long, Double> history = resource.getHistory();
        total_ = resource.getTotal();
        name_  = resource.getName();
        thresholds_ = resource.getThresholds();
        historySize_ = resource.getHistorySize();
        history_ = new LRUCache<Long, Double>(historySize_);
        for( Entry<Long, Double> historySet : history.entrySet())
        {
            if (historySet.getKey()>=pastTimestamp)
            {
                history_.put(historySet.getKey(), historySet.getValue());
            }
        }
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
     * @return the total
     */
    public double getTotal()
    {
        return total_;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total)
    {
        total_ = total;
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

    /**
     * @return the history
     */
    public LRUCache<Long, Double> getHistory()
    {
        return history_;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(LRUCache<Long, Double> history)
    {
        history_ = history;
    }

    /**
     * @return the historySize
     */
    public int getHistorySize()
    {
        return historySize_;
    }

    /**
     * @param historySize the historySize to set
     */
    public void setHistorySize(int historySize)
    {
        historySize_ = historySize;
        // For deserialization purpose.
        LRUCache<Long, Double> newHistory = new LRUCache<Long, Double>(historySize);
        newHistory.putAll(history_);
        history_ = newHistory;
    }
    
    
}
