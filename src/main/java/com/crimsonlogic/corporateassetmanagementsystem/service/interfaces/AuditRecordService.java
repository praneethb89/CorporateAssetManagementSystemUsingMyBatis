package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AuditRecord;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AuditStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuditException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuditRecordService {
    // Core Business Logic
    void recordAudit(Integer assetId, String auditorName, String auditNotes, AuditStatus auditStatus, LocalDate auditDate)
            throws AssetNotFoundException, AuditException, InvalidDataException;

    // Advanced Java 8 Stream API Methods
    List<AuditRecord> getFailedAudits();
    Map<AuditStatus, Long> countAuditsByStatus(); // Fixed the Map Key to AuditStatus
    Optional<AuditRecord> getLatestAuditForAsset(Integer assetId);
    List<String> getUniqueAuditors();
    List<AuditRecord> getAuditsConductedInYear(int year);
}