package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import java.util.List;

public interface AssetDao {
    void saveAsset(Asset asset);
    Asset findById(Integer id);
    List<Asset> findAll();
    void updateAsset(Asset asset);
    void deleteAsset(Integer id);
}