package org.inria.myriads.snoozecommon.virtualmachineimage;

import java.io.Serializable;
import java.util.ArrayList;

public class VirtualMachineImageList implements Serializable 
{

   /** Version Id.*/
    private static final long serialVersionUID = 1L;
    
    private ArrayList<VirtualMachineImage> images_;

    
    
    /**
     * 
     */
    public VirtualMachineImageList()
    {
        images_ = new ArrayList<VirtualMachineImage>();
    }

    
    /**
     * @return the images
     */
    public ArrayList<VirtualMachineImage> getImages()
    {
        return images_;
    }

   
    /**
     * @param images the images to set
     */
    public void setImages(ArrayList<VirtualMachineImage> images)
    {
        images_ = images;
    }
    

   
}
