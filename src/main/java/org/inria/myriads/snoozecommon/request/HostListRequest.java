package org.inria.myriads.snoozecommon.request;

public class HostListRequest {

   /** start. */
   private String start_ ; 
   
   /** Limit. */
   private int limit_;
   
   /** groupManagerId. */
   private String groupManagerId_;
   
   /** localControllerId. */
   private String localControllerId_;
   
   /** Number of monitoring entries.*/
   private int numberOfMonitoringEntries_;
   
    /**
     * Constructor. 
     */
    public HostListRequest() 
    {
        start_ = null;
        limit_ = 100;
        groupManagerId_ = null;
        localControllerId_ = null;
        numberOfMonitoringEntries_ = 0;
    }

    /**
     * @return the start
     */
    public String getStart() 
    {
        return start_;
    }
    
    /**
     * @param start the start to set
     */
    public void setStart(String start) 
    {
        start_ = start;
    }
    
    /**
     * @return the limit
     */
    public int getLimit() 
    {
        return limit_;
    }
    
    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) 
    {
        this.limit_ = limit;
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
     * @return the numberOfMonitoringEntries
     */
    public int getNumberOfMonitoringEntries() 
    {
        return numberOfMonitoringEntries_;
    }

    /**
     * @param numberOfMonitoringEntries the numberOfMonitoringEntries to set
     */
    public void setNumberOfMonitoringEntries(int numberOfMonitoringEntries)
    {
        numberOfMonitoringEntries_ = numberOfMonitoringEntries;
    }
   
   
}
