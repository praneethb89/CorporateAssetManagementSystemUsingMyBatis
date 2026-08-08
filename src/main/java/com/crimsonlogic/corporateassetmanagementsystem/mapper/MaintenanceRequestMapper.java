package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import java.util.List;

public interface MaintenanceRequestMapper {
    void insertMaintenanceRequest(MaintenanceRequest request);
    MaintenanceRequest getMaintenanceRequestById(Integer id);
    List<MaintenanceRequest> getAllMaintenanceRequests();
    List<MaintenanceRequest> getMaintenanceRequestsByAssetId(Integer assetId);
    void updateMaintenanceRequest(MaintenanceRequest request);
}