package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetAllocationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.time.LocalDate;
import java.util.List;

public interface AssetAllocationService {
    // Core Business Logic
    void allocateAsset(Integer assetId, Integer employeeId, String remarks, LocalDate allocationDate)
            throws AssetNotFoundException, AssetAllocationException, InvalidDataException;

    void returnAsset(Integer allocationId, LocalDate returnDate)
            throws AssetNotFoundException, InvalidDataException;

    // Advanced Java 8 Stream API Methods
    List<AssetAllocation> getAllActiveAllocations();
    List<AssetAllocation> getAllocationHistoryForEmployee(Integer employeeId);
    boolean isAssetCurrentlyAllocated(Integer assetId);
    long countActiveAllocations();
    List<AssetAllocation> getOverdueReturns(LocalDate expectedReturnDate);
}