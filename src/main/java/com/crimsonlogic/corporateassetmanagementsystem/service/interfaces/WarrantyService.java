package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Warranty;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.WarrantyException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WarrantyService {
    // Core Business Logic
    void addWarranty(Warranty warranty) throws AssetNotFoundException, WarrantyException, InvalidDataException;
    Warranty getWarrantyByAssetId(Integer assetId) throws AssetNotFoundException;

    // Advanced Java 8 Stream API Methods
    List<Warranty> getExpiringWarranties(LocalDate beforeDate);
    List<Warranty> getAllActiveWarranties();
    Map<String, Long> getWarrantyCountByProvider();
    Optional<LocalDate> getLatestWarrantyExpirationDate();
}