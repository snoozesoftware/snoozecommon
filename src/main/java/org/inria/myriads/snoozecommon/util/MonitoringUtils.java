/**
 * Copyright (C) 2010-2012 Eugen Feller, INRIA <eugen.feller@inria.fr>
 *
 * This file is part of Snooze, a scalable, autonomic, and
 * energy-aware virtual machine (VM) management framework.
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package org.inria.myriads.snoozecommon.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.inria.myriads.snoozecommon.communication.groupmanager.summary.GroupManagerSummaryInformation;
import org.inria.myriads.snoozecommon.communication.virtualcluster.monitoring.VirtualMachineMonitoringData;

/**
 * Monitoring utils.
 * 
 * @author Eugen Feller
 */
public final class MonitoringUtils 
{
    /** Newest monitoring data index. */
    private static int NEWEST_MONITORING_DATA_INDEX;
    
    /** Hide constructor. */
    private MonitoringUtils()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Returns latest virtual machine information.
     * 
     * @param dataMap   The data map
     * @return          The virtual machine monitoring data
     */
    public static VirtualMachineMonitoringData 
        getLatestVirtualMachineMonitoringData(Map<Long, VirtualMachineMonitoringData> dataMap)
    {
        List<VirtualMachineMonitoringData> data = new ArrayList<VirtualMachineMonitoringData>(dataMap.values());
        VirtualMachineMonitoringData monitoringData = data.get(NEWEST_MONITORING_DATA_INDEX);
        return monitoringData;
    }
    
    /**
     * Returns latest summary information.
     * 
     * @param dataMap   The data map
     * @return          The summary information
     */
    public static GroupManagerSummaryInformation 
        getLatestSummaryInformation(Map<Long, GroupManagerSummaryInformation> dataMap)
    {
        List<GroupManagerSummaryInformation> data = new ArrayList<GroupManagerSummaryInformation>(dataMap.values());
        GroupManagerSummaryInformation summaryData = data.get(NEWEST_MONITORING_DATA_INDEX);
        return summaryData;
    }
}
