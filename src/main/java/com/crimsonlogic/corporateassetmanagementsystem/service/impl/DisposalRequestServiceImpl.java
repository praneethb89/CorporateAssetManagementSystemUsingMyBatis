package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.DisposalRequestDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.EmployeeDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.DisposalRequestDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.EmployeeDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.enums.DisposalMethod;
import com.crimsonlogic.corporateassetmanagementsystem.enums.RequestStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DisposalException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DisposalRequestService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisposalRequestServiceImpl implements DisposalRequestService {

    private final DisposalRequestDao disposalDao;
    private final AssetDao assetDao;
    private final EmployeeDao employeeDao;

    public DisposalRequestServiceImpl() {
        this.disposalDao = new DisposalRequestDaoImpl();
        this.assetDao = new AssetDaoImpl();
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void submitDisposalRequest(Integer assetId, Integer requestedById, String disposalReason, DisposalMethod disposalMethod, Double disposalValue)
            throws AssetNotFoundException, DisposalException, InvalidDataException {

        Asset asset = assetDao.findById(assetId);
        if (asset == null) {
            throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
        }
        if (employeeDao.findById(requestedById) == null) {
            throw new InvalidDataException("Employee with ID " + requestedById + " does not exist.");
        }
        if (asset.getStatus() == AssetStatus.DISPOSED) {
            throw new DisposalException("This asset has already been disposed.");
        }
        if (disposalValue != null && disposalValue < 0) {
            throw new InvalidDataException("Disposal value cannot be negative.");
        }

        DisposalRequest request = new DisposalRequest();
        request.setAssetId(assetId);
        request.setRequestedById(requestedById);
        request.setDisposalReason(disposalReason);
        request.setDisposalMethod(disposalMethod); // Fixed: Now directly accepts the Enum
        request.setDisposalValue(disposalValue);
        request.setRequestStatus(RequestStatus.PENDING); // Fixed: Uses Enum
        request.setRequestDate(LocalDate.now());

        disposalDao.saveDisposalRequest(request);
    }

    @Override
    public void processDisposalRequest(Integer requestId, RequestStatus newStatus)
            throws DisposalException, InvalidDataException, AssetNotFoundException {

        DisposalRequest request = disposalDao.findById(requestId);
        if (request == null) {
            throw new DisposalException("Disposal request not found.");
        }
        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new DisposalException("Only PENDING requests can be processed. This request is already " + request.getRequestStatus());
        }

        if (newStatus != RequestStatus.APPROVED && newStatus != RequestStatus.REJECTED) {
            throw new InvalidDataException("Status must be APPROVED or REJECTED.");
        }

        // Update the request status
        request.setRequestStatus(newStatus);
        disposalDao.updateDisposalRequest(request);

        // If APPROVED, we must permanently retire the asset
        if (newStatus == RequestStatus.APPROVED) {
            Asset asset = assetDao.findById(request.getAssetId());
            if (asset != null) {
                asset.setStatus(AssetStatus.DISPOSED);
                assetDao.updateAsset(asset);
            }
        }
    }

    // --- JAVA 8 STREAM API IMPLEMENTATIONS ---

    @Override
    public List<DisposalRequest> getPendingRequests() {
        return disposalDao.findAll().stream()
                .filter(req -> req.getRequestStatus() == RequestStatus.PENDING) // Fixed: Enum comparison
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalRecoveredValue() {
        return disposalDao.findAll().stream()
                .filter(req -> req.getRequestStatus() == RequestStatus.APPROVED) // Fixed: Enum comparison
                .filter(req -> req.getDisposalValue() != null)
                .mapToDouble(DisposalRequest::getDisposalValue)
                .sum();
    }

    @Override
    public Map<DisposalMethod, Long> countDisposalsByMethod() {
        return disposalDao.findAll().stream()
                .filter(req -> req.getDisposalMethod() != null)
                .collect(Collectors.groupingBy(DisposalRequest::getDisposalMethod, Collectors.counting()));
    }

    @Override
    public Optional<DisposalRequest> getHighestValueDisposal() {
        return disposalDao.findAll().stream()
                .filter(req -> req.getRequestStatus() == RequestStatus.APPROVED) // Fixed: Enum comparison
                .filter(req -> req.getDisposalValue() != null)
                .max(Comparator.comparing(DisposalRequest::getDisposalValue));
    }

    @Override
    public List<DisposalRequest> getDisposalHistoryByEmployee(Integer employeeId) {
        return disposalDao.findAll().stream()
                .filter(req -> req.getRequestedById().equals(employeeId))
                .collect(Collectors.toList());
    }
}