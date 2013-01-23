package org.inria.myriads.snoozecommon.communication.virtualmachine;

import java.io.Serializable;
import java.util.ArrayList;

import org.inria.myriads.snoozecommon.communication.virtualcluster.submission.VirtualMachineLocation;

/**
 * 
 * Resize Request.
 * 
 * @author msimonin
 *
 */
public class ResizeRequest implements Serializable 
{
     /** Serial Id. */
    private static final long serialVersionUID = 1L;
    
    /** Virtual Machine Id.*/
    private VirtualMachineLocation virtualMachineLocation_;
    
    /** the resized capacity.*/
    private ArrayList<Double> resizedCapacity_;

    /**
     * Constructor.
     */
    public ResizeRequest() 
    {
        virtualMachineLocation_ = new VirtualMachineLocation();
    }

    /**
     * Constructor.
     * 
     * @param resizeRequest         The resize request.
     * 
     */
    public ResizeRequest(ResizeRequest resizeRequest)
    {
        virtualMachineLocation_ = resizeRequest.getVirtualMachineLocation();
        resizedCapacity_ = resizeRequest.getResizedCapacity();
    }
    
    /**
     * @return the resizedCapacity
     */
    public ArrayList<Double> getResizedCapacity() 
    {
        return resizedCapacity_;
    }

    /**
     * @param resizedCapacity the resizedCapacity to set
     */
    public void setResizedCapacity(ArrayList<Double> resizedCapacity) 
    {
        resizedCapacity_ = resizedCapacity;
    }

    /**
     * @return the virtualMachineLocation
     */
    public VirtualMachineLocation getVirtualMachineLocation() 
    {
        return virtualMachineLocation_;
    }

    /**
     * @param virtualMachineLocation the virtualMachineLocation to set
     */
    public void setVirtualMachineLocation(
            VirtualMachineLocation virtualMachineLocation)
    {
        virtualMachineLocation_ = virtualMachineLocation;
    }
}
