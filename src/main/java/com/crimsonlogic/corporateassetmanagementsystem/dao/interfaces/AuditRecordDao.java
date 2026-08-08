package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AuditRecord;
import java.util.List;

public interface AuditRecordDao {
    void saveAuditRecord(AuditRecord auditRecord);
    AuditRecord findById(Integer id);
    List<AuditRecord> findAll();
    List<AuditRecord> findByAssetId(Integer assetId);
}