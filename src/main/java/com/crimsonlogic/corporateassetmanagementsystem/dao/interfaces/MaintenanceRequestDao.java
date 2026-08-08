package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import java.util.List;

public interface MaintenanceRequestDao {
    void saveMaintenanceRequest(MaintenanceRequest request);
    MaintenanceRequest findById(Integer id);
    List<MaintenanceRequest> findAll();
    List<MaintenanceRequest> findByAssetId(Integer assetId);
    void updateMaintenanceRequest(MaintenanceRequest request);
}