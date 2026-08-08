package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import com.crimsonlogic.corporateassetmanagementsystem.enums.MaintenanceStatus;

public class MaintenanceRequest extends BaseEntity {

    private String issueDescription;
    private Double cost;
    private MaintenanceStatus status; // e.g., Pending, In Progress, Completed

    // Foreign Key
    private Integer assetId;

    public MaintenanceRequest() {
        super();
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "id=" + getId() +
                ", issueDescription='" + issueDescription + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                ", assetId=" + assetId +
                '}';
    }
}