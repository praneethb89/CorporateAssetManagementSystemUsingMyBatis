package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AssetService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AssetServiceImpl implements AssetService {

    private final AssetDao assetDao;

    public AssetServiceImpl() {
        this.assetDao = new AssetDaoImpl();
    }

    @Override
    public void addAsset(Asset asset) throws InvalidDataException {
        if (asset.getPurchaseCost() == null || asset.getPurchaseCost() < 0) {
            throw new InvalidDataException("Asset purchase cost cannot be negative.");
        }
        if (asset.getAssetName() == null || asset.getAssetName().trim().isEmpty()) {
            throw new InvalidDataException("Asset name cannot be empty.");
        }

        // Default to AVAILABLE if status is missing
        if (asset.getStatus() == null) {
            asset.setStatus(AssetStatus.AVAILABLE);
        }

        assetDao.saveAsset(asset);
    }

    @Override
    public void updateAsset(Asset asset) throws AssetNotFoundException, InvalidDataException {
        if (assetDao.findById(asset.getId()) == null) {
            throw new AssetNotFoundException("Cannot update. Asset with ID " + asset.getId() + " not found.");
        }
        if (asset.getPurchaseCost() != null && asset.getPurchaseCost() < 0) {
            throw new InvalidDataException("Asset purchase cost cannot be negative.");
        }
        assetDao.updateAsset(asset);
    }

    @Override
    public void deleteAsset(Integer id) throws AssetNotFoundException {
        if (assetDao.findById(id) == null) {
            throw new AssetNotFoundException("Cannot delete. Asset with ID " + id + " not found.");
        }
        assetDao.deleteAsset(id);
    }

    @Override
    public Asset getAssetById(Integer id) throws AssetNotFoundException {
        Asset asset = assetDao.findById(id);
        if (asset == null) {
            throw new AssetNotFoundException("Asset with ID " + id + " not found.");
        }
        return asset;
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetDao.findAll();
    }

    // --- JAVA 8 STREAM API IMPLEMENTATIONS ---

    @Override
    public List<Asset> getAssetsByStatus(AssetStatus status) {
        // Stream #1: filter()
        return assetDao.findAll().stream()
                .filter(asset -> asset.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Asset> sortAssetsByPurchaseCost(boolean ascending) {
        // Stream #2: sorted() with custom Comparator
        Comparator<Asset> costComparator = Comparator.comparing(Asset::getPurchaseCost, Comparator.nullsLast(Double::compareTo));
        if (!ascending) {
            costComparator = costComparator.reversed();
        }

        return assetDao.findAll().stream()
                .sorted(costComparator)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalAssetValue() {
        // Stream #3: mapToDouble() and sum()
        return assetDao.findAll().stream()
                .filter(asset -> asset.getPurchaseCost() != null)
                .mapToDouble(Asset::getPurchaseCost)
                .sum();
    }

    @Override
    public Map<AssetStatus, Long> getAssetCountByStatus() {
        // Stream #4: collect() with groupingBy() and counting()
        return assetDao.findAll().stream()
                .collect(Collectors.groupingBy(Asset::getStatus, Collectors.counting()));
    }

    @Override
    public Optional<Asset> getMostExpensiveAsset() {
        // Stream #5: max() with Comparator
        return assetDao.findAll().stream()
                .filter(asset -> asset.getPurchaseCost() != null)
                .max(Comparator.comparing(Asset::getPurchaseCost));
    }

    @Override
    public List<Asset> getAssetsPurchasedAfter(LocalDate date) {
        // Stream #6: filter() with java.time logic
        return assetDao.findAll().stream()
                .filter(asset -> asset.getPurchaseDate() != null && asset.getPurchaseDate().isAfter(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getUniqueVendorIds() {
        // Stream #7 & #8: map() and distinct()
        return assetDao.findAll().stream()
                .map(Asset::getVendorId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());
    }
}