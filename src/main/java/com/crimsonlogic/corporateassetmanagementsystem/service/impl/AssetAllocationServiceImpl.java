package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetAllocationDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.EmployeeDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetAllocationDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.EmployeeDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetAllocationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AssetAllocationService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AssetAllocationServiceImpl implements AssetAllocationService {

    private final AssetAllocationDao allocationDao;
    private final AssetDao assetDao;
    private final EmployeeDao employeeDao;

    public AssetAllocationServiceImpl() {
        this.allocationDao = new AssetAllocationDaoImpl();
        this.assetDao = new AssetDaoImpl();
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void allocateAsset(Integer assetId, Integer employeeId, String remarks, LocalDate allocationDate)
            throws AssetNotFoundException, AssetAllocationException, InvalidDataException {

        Asset asset = assetDao.findById(assetId);
        if (asset == null) {
            throw new AssetNotFoundException("Cannot allocate. Asset with ID " + assetId + " not found.");
        }
        if (employeeDao.findById(employeeId) == null) {
            throw new InvalidDataException("Employee with ID " + employeeId + " does not exist.");
        }

        // Business Rule: Can only allocate AVAILABLE assets
        if (asset.getStatus() != AssetStatus.AVAILABLE) {
            throw new AssetAllocationException("Asset is currently " + asset.getStatus() + " and cannot be allocated.");
        }

        // Create the allocation record
        AssetAllocation allocation = new AssetAllocation();
        allocation.setAssetId(assetId);
        allocation.setEmployeeId(employeeId);
        allocation.setAllocationDate(allocationDate);
        allocation.setRemarks(remarks);

        // Save to DB
        allocationDao.saveAllocation(allocation);

        // Update the Asset's status to ALLOCATED
        asset.setStatus(AssetStatus.ALLOCATED);
        assetDao.updateAsset(asset);
    }

    @Override
    public void returnAsset(Integer allocationId, LocalDate returnDate)
            throws AssetNotFoundException, InvalidDataException {

        AssetAllocation allocation = allocationDao.findById(allocationId);
        if (allocation == null) {
            throw new AssetNotFoundException("Allocation record not found.");
        }
        if (allocation.getReturnDate() != null) {
            throw new InvalidDataException("This asset has already been returned.");
        }

        // Update allocation with return date
        allocation.setReturnDate(returnDate);
        allocationDao.updateAllocation(allocation);

        // Make the asset AVAILABLE again
        Asset asset = assetDao.findById(allocation.getAssetId());
        if (asset != null) {
            asset.setStatus(AssetStatus.AVAILABLE);
            assetDao.updateAsset(asset);
        }
    }

    // --- JAVA 8 STREAM API IMPLEMENTATIONS ---

    @Override
    public List<AssetAllocation> getAllActiveAllocations() {
        // Stream #9: filter() records where returnDate is null
        return allocationDao.findAll().stream()
                .filter(alloc -> alloc.getReturnDate() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetAllocation> getAllocationHistoryForEmployee(Integer employeeId) {
        // Stream #10: filter() and sorted() by allocation date descending
        return allocationDao.findByEmployeeId(employeeId).stream()
                .sorted((a1, a2) -> a2.getAllocationDate().compareTo(a1.getAllocationDate()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAssetCurrentlyAllocated(Integer assetId) {
        // Stream #11: anyMatch() checks if there is any active allocation for this asset
        return allocationDao.findByAssetId(assetId).stream()
                .anyMatch(alloc -> alloc.getReturnDate() == null);
    }

    @Override
    public long countActiveAllocations() {
        // Stream #12: count()
        return allocationDao.findAll().stream()
                .filter(alloc -> alloc.getReturnDate() == null)
                .count();
    }

    @Override
    public List<AssetAllocation> getOverdueReturns(LocalDate expectedReturnDate) {
        // Stream #13: filter() combined conditions
        return allocationDao.findAll().stream()
                .filter(alloc -> alloc.getReturnDate() == null)
                .filter(alloc -> alloc.getAllocationDate().isBefore(expectedReturnDate))
                .collect(Collectors.toList());
    }
}