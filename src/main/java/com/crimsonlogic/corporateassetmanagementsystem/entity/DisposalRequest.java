package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import com.crimsonlogic.corporateassetmanagementsystem.enums.DisposalMethod;
import com.crimsonlogic.corporateassetmanagementsystem.enums.RequestStatus;

import java.time.LocalDate;

public class DisposalRequest extends BaseEntity {

    private LocalDate requestDate;
    private String disposalReason;
    private DisposalMethod disposalMethod; // e.g., Sold, Scrapped, Donated
    private Double disposalValue;
    private RequestStatus requestStatus; // e.g., Pending, Approved, Rejected

    // Foreign Keys
    private Integer assetId;
    private Integer requestedById; // Maps to Employee ID

    public DisposalRequest() {
        super();
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getDisposalReason() {
        return disposalReason;
    }

    public void setDisposalReason(String disposalReason) {
        this.disposalReason = disposalReason;
    }

    public DisposalMethod getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(DisposalMethod disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getRequestedById() {
        return requestedById;
    }

    public void setRequestedById(Integer requestedById) {
        this.requestedById = requestedById;
    }

    @Override
    public String toString() {
        return "DisposalRequest{" +
                "id=" + getId() +
                ", requestDate=" + requestDate +
                ", disposalReason='" + disposalReason + '\'' +
                ", disposalMethod='" + disposalMethod + '\'' +
                ", disposalValue=" + disposalValue +
                ", requestStatus='" + requestStatus + '\'' +
                ", assetId=" + assetId +
                ", requestedById=" + requestedById +
                '}';
    }
}