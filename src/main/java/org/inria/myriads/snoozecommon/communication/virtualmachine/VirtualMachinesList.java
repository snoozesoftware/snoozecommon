package org.inria.myriads.snoozecommon.communication.virtualmachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.inria.myriads.snoozecommon.communication.virtualcluster.VirtualMachineMetaData;

public class VirtualMachinesList implements Serializable
{

    /** Serial id.*/
    private static final long serialVersionUID = 1L;
    
    
    /** Virtual machines*/
    private List<VirtualMachineMetaData> virtualMachines_;


    /**
     * Constructor 
     */
    public VirtualMachinesList()
    {
        virtualMachines_ = new ArrayList<VirtualMachineMetaData>();
    }


    /**
     * @return the virtualMachines
     */
    public List<VirtualMachineMetaData> getVirtualMachines()
    {
        return virtualMachines_;
    }


    /**
     * @param virtualMachines the virtualMachines to set
     */
    public void setVirtualMachines(List<VirtualMachineMetaData> virtualMachines)
    {
        virtualMachines_ = virtualMachines;
    }
        
    public void add(VirtualMachineMetaData virtualMachine)
    {
        virtualMachines_.add(virtualMachine);
    }
} 
