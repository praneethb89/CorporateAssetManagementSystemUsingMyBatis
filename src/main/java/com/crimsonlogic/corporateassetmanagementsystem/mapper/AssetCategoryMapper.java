package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetCategory;
import java.util.List;

public interface AssetCategoryMapper {
    void insertAssetCategory(AssetCategory category);
    AssetCategory getAssetCategoryById(Integer id);
    List<AssetCategory> getAllAssetCategories();
    void updateAssetCategory(AssetCategory category);
    void deleteAssetCategory(Integer id);
}