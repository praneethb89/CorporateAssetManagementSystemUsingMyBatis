package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetCategory;
import java.util.List;

public interface AssetCategoryDao {
    void saveAssetCategory(AssetCategory category);
    AssetCategory findById(Integer id);
    List<AssetCategory> findAll();
    void updateAssetCategory(AssetCategory category);
    void deleteAssetCategory(Integer id);
}