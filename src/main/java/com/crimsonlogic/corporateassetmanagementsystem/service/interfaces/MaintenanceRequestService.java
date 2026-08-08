package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import com.crimsonlogic.corporateassetmanagementsystem.enums.MaintenanceStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.MaintenanceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MaintenanceRequestService {
    // Core Business Logic
    void scheduleMaintenance(Integer assetId, String issueDescription, Double estimatedCost)
            throws AssetNotFoundException, MaintenanceException, InvalidDataException;

    void completeMaintenance(Integer maintenanceId, Double finalCost)
            throws AssetNotFoundException, MaintenanceException, InvalidDataException;

    // Advanced Java 8 Stream API Methods
    List<MaintenanceRequest> getActiveMaintenanceRequests();
    double getTotalMaintenanceCostForAsset(Integer assetId);
    Map<MaintenanceStatus, List<MaintenanceRequest>> groupMaintenanceByStatus(); // Fixed the Map Key
    Optional<MaintenanceRequest> getCostliestMaintenanceRecord();
}