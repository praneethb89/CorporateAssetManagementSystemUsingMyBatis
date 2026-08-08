package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AssetService {
    // Core Business Logic
    void addAsset(Asset asset) throws InvalidDataException;
    void updateAsset(Asset asset) throws AssetNotFoundException, InvalidDataException;
    void deleteAsset(Integer id) throws AssetNotFoundException;
    Asset getAssetById(Integer id) throws AssetNotFoundException;

    // Advanced Java 8 Stream API Methods
    List<Asset> getAllAssets();
    List<Asset> getAssetsByStatus(AssetStatus status);
    List<Asset> sortAssetsByPurchaseCost(boolean ascending);
    double calculateTotalAssetValue();
    Map<AssetStatus, Long> getAssetCountByStatus();
    Optional<Asset> getMostExpensiveAsset();
    List<Asset> getAssetsPurchasedAfter(LocalDate date);
    List<Integer> getUniqueVendorIds();
}