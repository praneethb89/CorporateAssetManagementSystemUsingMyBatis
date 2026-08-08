package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AuditRecord;
import java.util.List;

public interface AuditRecordMapper {
    void insertAuditRecord(AuditRecord auditRecord);
    AuditRecord getAuditRecordById(Integer id);
    List<AuditRecord> getAllAuditRecords();
    List<AuditRecord> getAuditRecordsByAssetId(Integer assetId);
}