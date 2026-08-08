package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import java.util.List;

public interface AssetAllocationMapper {
    void insertAllocation(AssetAllocation allocation);
    AssetAllocation getAllocationById(Integer id);
    List<AssetAllocation> getAllAllocations();
    List<AssetAllocation> getAllocationsByEmployeeId(Integer employeeId);
    List<AssetAllocation> getAllocationsByAssetId(Integer assetId);
    void updateAllocation(AssetAllocation allocation);
}