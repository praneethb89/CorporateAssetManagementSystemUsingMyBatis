package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AssetDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.AuditRecordDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AuditRecordDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.AuditRecord;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AuditStatus;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AssetNotFoundException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuditException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AuditRecordService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuditRecordServiceImpl implements AuditRecordService {

    private final AuditRecordDao auditDao;
    private final AssetDao assetDao;

    public AuditRecordServiceImpl() {
        this.auditDao = new AuditRecordDaoImpl();
        this.assetDao = new AssetDaoImpl();
    }

    @Override
    public void recordAudit(Integer assetId, String auditorName, String auditNotes, AuditStatus auditStatus, LocalDate auditDate)
            throws AssetNotFoundException, AuditException, InvalidDataException {

        if (assetDao.findById(assetId) == null) {
            throw new AssetNotFoundException("Cannot record audit. Asset with ID " + assetId + " not found.");
        }

        if (auditorName == null || auditorName.trim().isEmpty()) {
            throw new InvalidDataException("Auditor name cannot be empty.");
        }

        if (auditStatus == null) {
            throw new AuditException("Audit status cannot be null.");
        }

        AuditRecord record = new AuditRecord();
        record.setAssetId(assetId);
        record.setAuditorName(auditorName);
        record.setAuditNotes(auditNotes);
        record.setAuditStatus(auditStatus); // Fixed: Directly setting the Enum
        record.setAuditDate(auditDate != null ? auditDate : LocalDate.now());

        auditDao.saveAuditRecord(record);
    }

    @Override
    public List<AuditRecord> getFailedAudits() {
        return auditDao.findAll().stream()
                .filter(record -> record.getAuditStatus() == AuditStatus.FAILED) // Fixed: Enum comparison
                .collect(Collectors.toList());
    }

    @Override
    public Map<AuditStatus, Long> countAuditsByStatus() {
        return auditDao.findAll().stream()
                .filter(record -> record.getAuditStatus() != null)
                .collect(Collectors.groupingBy(AuditRecord::getAuditStatus, Collectors.counting()));
    }

    @Override
    public Optional<AuditRecord> getLatestAuditForAsset(Integer assetId) {
        return auditDao.findByAssetId(assetId).stream()
                .filter(record -> record.getAuditDate() != null)
                .max(Comparator.comparing(AuditRecord::getAuditDate));
    }

    @Override
    public List<String> getUniqueAuditors() {
        return auditDao.findAll().stream()
                .map(AuditRecord::getAuditorName)
                .filter(name -> name != null && !name.trim().isEmpty())
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditRecord> getAuditsConductedInYear(int year) {
        return auditDao.findAll().stream()
                .filter(record -> record.getAuditDate() != null && record.getAuditDate().getYear() == year)
                .collect(Collectors.toList());
    }
}