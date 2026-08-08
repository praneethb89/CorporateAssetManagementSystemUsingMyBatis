package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.MaintenanceRequestDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.MaintenanceRequestDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.enums.MaintenanceStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.MaintenanceException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.MaintenanceRequestService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private final MaintenanceRequestDao maintenanceDao;
    private final AssetDao assetDao;

    public MaintenanceRequestServiceImpl() {
        this.maintenanceDao = new MaintenanceRequestDaoImpl();
        this.assetDao = new AssetDaoImpl();
    }

    @Override
    public void scheduleMaintenance(Integer assetId, String issueDescription, Double estimatedCost)
            throws AssetNotFoundException, MaintenanceException, InvalidDataException {

        Asset asset = assetDao.findById(assetId);
        if (asset == null) {
            throw new AssetNotFoundException("Cannot schedule maintenance. Asset with ID " + assetId + " not found.");
        }

        if (issueDescription == null || issueDescription.trim().isEmpty()) {
            throw new InvalidDataException("Issue description cannot be empty.");
        }

        if (asset.getStatus() == AssetStatus.DISPOSED || asset.getStatus() == AssetStatus.MAINTENANCE) {
            throw new MaintenanceException("Asset is currently " + asset.getStatus() + " and cannot be scheduled for maintenance.");
        }

        MaintenanceRequest request = new MaintenanceRequest();
        request.setAssetId(assetId);
        request.setIssueDescription(issueDescription);
        request.setCost(estimatedCost);
        request.setStatus(MaintenanceStatus.PENDING); // Fixed: Uses Enum

        maintenanceDao.saveMaintenanceRequest(request);

        asset.setStatus(AssetStatus.MAINTENANCE);
        assetDao.updateAsset(asset);
    }

    @Override
    public void completeMaintenance(Integer maintenanceId, Double finalCost)
            throws AssetNotFoundException, MaintenanceException, InvalidDataException {

        MaintenanceRequest request = maintenanceDao.findById(maintenanceId);
        if (request == null) {
            throw new AssetNotFoundException("Maintenance request not found.");
        }

        if (request.getStatus() == MaintenanceStatus.COMPLETED) { // Fixed: Enum comparison
            throw new MaintenanceException("This maintenance request is already completed.");
        }
        if (finalCost != null && finalCost < 0) {
            throw new InvalidDataException("Maintenance cost cannot be negative.");
        }

        request.setStatus(MaintenanceStatus.COMPLETED); // Fixed: Uses Enum
        if (finalCost != null) {
            request.setCost(finalCost);
        }
        maintenanceDao.updateMaintenanceRequest(request);

        Asset asset = assetDao.findById(request.getAssetId());
        if (asset != null) {
            asset.setStatus(AssetStatus.AVAILABLE);
            assetDao.updateAsset(asset);
        }
    }

    @Override
    public List<MaintenanceRequest> getActiveMaintenanceRequests() {
        return maintenanceDao.findAll().stream()
                .filter(req -> req.getStatus() != MaintenanceStatus.COMPLETED) // Fixed: Enum comparison
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalMaintenanceCostForAsset(Integer assetId) {
        return maintenanceDao.findByAssetId(assetId).stream()
                .filter(req -> req.getCost() != null)
                .mapToDouble(MaintenanceRequest::getCost)
                .sum();
    }

    @Override
    public Map<MaintenanceStatus, List<MaintenanceRequest>> groupMaintenanceByStatus() {
        return maintenanceDao.findAll().stream()
                .collect(Collectors.groupingBy(MaintenanceRequest::getStatus));
    }

    @Override
    public Optional<MaintenanceRequest> getCostliestMaintenanceRecord() {
        return maintenanceDao.findAll().stream()
                .filter(req -> req.getCost() != null)
                .max(Comparator.comparing(MaintenanceRequest::getCost));
    }
}