package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AuditStatus;

import java.time.LocalDate;

public class AuditRecord extends BaseEntity {

    private LocalDate auditDate;
    private String auditorName;
    private String auditNotes;
    private AuditStatus auditStatus; // e.g., Passed, Failed, Needs Review

    // Foreign Key
    private Integer assetId;

    public AuditRecord() {
        super();
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditNotes() {
        return auditNotes;
    }

    public void setAuditNotes(String auditNotes) {
        this.auditNotes = auditNotes;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "id=" + getId() +
                ", auditDate=" + auditDate +
                ", auditorName='" + auditorName + '\'' +
                ", auditNotes='" + auditNotes + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", assetId=" + assetId +
                '}';
    }
}