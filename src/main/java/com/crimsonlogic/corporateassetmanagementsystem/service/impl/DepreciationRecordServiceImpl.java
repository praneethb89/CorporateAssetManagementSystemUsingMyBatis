package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.DepreciationRecordDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.DepreciationRecordDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.entity.DepreciationRecord;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DepreciationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DepreciationRecordService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class DepreciationRecordServiceImpl implements DepreciationRecordService {

    private final DepreciationRecordDao depreciationDao;
    private final AssetDao assetDao;

    public DepreciationRecordServiceImpl() {
        this.depreciationDao = new DepreciationRecordDaoImpl();
        this.assetDao = new AssetDaoImpl();
    }

    @Override
    public void recordDepreciation(Integer assetId, Double depreciationAmount, LocalDate calculationDate)
            throws AssetNotFoundException, DepreciationException, InvalidDataException {

        Asset asset = assetDao.findById(assetId);
        if (asset == null) {
            throw new AssetNotFoundException("Cannot record depreciation. Asset with ID " + assetId + " not found.");
        }

        if (depreciationAmount == null || depreciationAmount <= 0) {
            throw new InvalidDataException("Depreciation amount must be greater than zero.");
        }

        // Business Rule: Total depreciation cannot exceed the asset's purchase cost.
        // We use our own Stream API method below to calculate the current total!
        double currentTotalDepreciation = getTotalDepreciationForAsset(assetId);
        double assetCost = asset.getPurchaseCost() != null ? asset.getPurchaseCost() : 0.0;

        if ((currentTotalDepreciation + depreciationAmount) > assetCost) {
            throw new DepreciationException("Invalid entry: Total accumulated depreciation ("
                    + (currentTotalDepreciation + depreciationAmount)
                    + ") would exceed the asset's original purchase cost (" + assetCost + ").");
        }

        DepreciationRecord record = new DepreciationRecord();
        record.setAssetId(assetId);
        record.setDepreciationAmount(depreciationAmount);
        record.setCalculationDate(calculationDate != null ? calculationDate : LocalDate.now());

        depreciationDao.saveDepreciationRecord(record);
    }

    @Override
    public List<DepreciationRecord> getRecordsByAssetId(Integer assetId) throws AssetNotFoundException {
        if (assetDao.findById(assetId) == null) {
            throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
        }
        return depreciationDao.findByAssetId(assetId);
    }

    // --- JAVA 8 STREAM API IMPLEMENTATIONS ---

    @Override
    public double getTotalDepreciationForAsset(Integer assetId) {
        // Stream #22: mapToDouble() and sum() to find accumulated depreciation
        return depreciationDao.findByAssetId(assetId).stream()
                .filter(record -> record.getDepreciationAmount() != null)
                .mapToDouble(DepreciationRecord::getDepreciationAmount)
                .sum();
    }

    @Override
    public OptionalDouble getAverageDepreciationAmount() {
        // Stream #23: mapToDouble() and average() across the entire company
        return depreciationDao.findAll().stream()
                .filter(record -> record.getDepreciationAmount() != null)
                .mapToDouble(DepreciationRecord::getDepreciationAmount)
                .average();
    }

    @Override
    public List<DepreciationRecord> getRecordsAboveAmount(Double threshold) {
        // Stream #24: filter() for high-value depreciation hits
        return depreciationDao.findAll().stream()
                .filter(record -> record.getDepreciationAmount() != null && record.getDepreciationAmount() > threshold)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepreciationRecord> getLatestDepreciationRecord(Integer assetId) {
        // Stream #25: filter() and max() with Comparator to find the most recent record
        return depreciationDao.findByAssetId(assetId).stream()
                .filter(record -> record.getCalculationDate() != null)
                .max(Comparator.comparing(DepreciationRecord::getCalculationDate));
    }
}