package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import java.util.List;

public interface AssetAllocationDao {
    void saveAllocation(AssetAllocation allocation);
    AssetAllocation findById(Integer id);
    List<AssetAllocation> findAll();
    List<AssetAllocation> findByEmployeeId(Integer employeeId);
    List<AssetAllocation> findByAssetId(Integer assetId);
    void updateAllocation(AssetAllocation allocation);
}