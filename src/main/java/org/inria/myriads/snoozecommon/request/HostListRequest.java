package org.inria.myriads.snoozecommon.request;

public class HostListRequest {

   /** start. */
   private String start_ ; 
   
   /** Limit. */
   private int limit_;

    /**
     * Constructor. 
     */
    public HostListRequest() 
    {
        start_ = null;
        limit_ = 100;
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
   
   
}
