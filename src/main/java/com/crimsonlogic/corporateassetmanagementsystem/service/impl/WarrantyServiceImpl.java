package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.WarrantyDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.WarrantyDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Warranty;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.WarrantyException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.WarrantyService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyDao warrantyDao;
    private final AssetDao assetDao;

    public WarrantyServiceImpl() {
        this.warrantyDao = new WarrantyDaoImpl();
        this.assetDao = new AssetDaoImpl();
    }

    @Override
    public void addWarranty(Warranty warranty) throws AssetNotFoundException, WarrantyException, InvalidDataException {
        if (warranty.getAssetId() == null) {
            throw new InvalidDataException("Asset ID cannot be null when adding a warranty.");
        }

        if (assetDao.findById(warranty.getAssetId()) == null) {
            throw new AssetNotFoundException("Cannot add warranty. Asset with ID " + warranty.getAssetId() + " not found.");
        }

        if (warranty.getExpirationDate() == null || warranty.getExpirationDate().isBefore(LocalDate.now())) {
            throw new InvalidDataException("Warranty expiration date must be in the future.");
        }

        // Business Rule: One warranty per asset. Check if it already exists.
        Warranty existingWarranty = warrantyDao.findByAssetId(warranty.getAssetId());
        if (existingWarranty != null) {
            throw new WarrantyException("Asset with ID " + warranty.getAssetId() + " already has an active warranty.");
        }

        warrantyDao.saveWarranty(warranty);
    }

    @Override
    public Warranty getWarrantyByAssetId(Integer assetId) throws AssetNotFoundException {
        Warranty warranty = warrantyDao.findByAssetId(assetId);
        if (warranty == null) {
            throw new AssetNotFoundException("No warranty found for Asset ID " + assetId);
        }
        return warranty;
    }

    // --- JAVA 8 STREAM API IMPLEMENTATIONS ---

    @Override
    public List<Warranty> getExpiringWarranties(LocalDate beforeDate) {
        // Stream #18: filter() to find warranties expiring before a certain date (great for alerting admins!)
        return warrantyDao.findAll().stream()
                .filter(w -> w.getExpirationDate() != null && w.getExpirationDate().isBefore(beforeDate))
                .filter(w -> w.getExpirationDate().isAfter(LocalDate.now())) // Still active, but expiring soon
                .collect(Collectors.toList());
    }

    @Override
    public List<Warranty> getAllActiveWarranties() {
        // Stream #19: filter() out warranties that have already expired
        LocalDate today = LocalDate.now();
        return warrantyDao.findAll().stream()
                .filter(w -> w.getExpirationDate() != null && w.getExpirationDate().isAfter(today))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getWarrantyCountByProvider() {
        // Stream #20: groupingBy() and counting() to see which provider handles most of our assets
        return warrantyDao.findAll().stream()
                .filter(w -> w.getProvider() != null && !w.getProvider().trim().isEmpty())
                .collect(Collectors.groupingBy(Warranty::getProvider, Collectors.counting()));
    }

    @Override
    public Optional<LocalDate> getLatestWarrantyExpirationDate() {
        // Stream #21: map() and max() to find the furthest warranty expiration date in our system
        return warrantyDao.findAll().stream()
                .map(Warranty::getExpirationDate)
                .filter(date -> date != null)
                .max(LocalDate::compareTo);
    }
}