package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import com.crimsonlogic.corporateassetmanagementsystem.enums.DisposalMethod;
import com.crimsonlogic.corporateassetmanagementsystem.enums.RequestStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DisposalException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DisposalRequestService {
    // Core Business Logic
    void submitDisposalRequest(Integer assetId, Integer requestedById, String disposalReason, DisposalMethod disposalMethod, Double disposalValue)
            throws AssetNotFoundException, DisposalException, InvalidDataException;

    void processDisposalRequest(Integer requestId, RequestStatus newStatus)
            throws DisposalException, InvalidDataException, AssetNotFoundException;

    // Advanced Java 8 Stream API Methods (31 - 35)
    List<DisposalRequest> getPendingRequests();
    double getTotalRecoveredValue();
    Map<DisposalMethod, Long> countDisposalsByMethod();
    Optional<DisposalRequest> getHighestValueDisposal();
    List<DisposalRequest> getDisposalHistoryByEmployee(Integer employeeId);
}