package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DepreciationRecord;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DepreciationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface DepreciationRecordService {
    // Core Business Logic
    void recordDepreciation(Integer assetId, Double depreciationAmount, LocalDate calculationDate)
            throws AssetNotFoundException, DepreciationException, InvalidDataException;

    List<DepreciationRecord> getRecordsByAssetId(Integer assetId) throws AssetNotFoundException;

    // Advanced Java 8 Stream API Methods
    double getTotalDepreciationForAsset(Integer assetId);
    OptionalDouble getAverageDepreciationAmount();
    List<DepreciationRecord> getRecordsAboveAmount(Double threshold);
    Optional<DepreciationRecord> getLatestDepreciationRecord(Integer assetId);
}