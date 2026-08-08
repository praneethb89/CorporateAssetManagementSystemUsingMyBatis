package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import java.util.List;

public interface AssetMapper {
    void insertAsset(Asset asset);
    Asset getAssetById(Integer id);
    List<Asset> getAllAssets();
    void updateAsset(Asset asset);
    void deleteAsset(Integer id); // Soft delete
}