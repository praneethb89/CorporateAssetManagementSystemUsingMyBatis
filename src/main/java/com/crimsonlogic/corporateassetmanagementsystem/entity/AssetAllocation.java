package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import java.time.LocalDate;

public class AssetAllocation extends BaseEntity {

    private LocalDate allocationDate;
    private LocalDate returnDate;
    private String remarks;

    // Foreign Keys
    private Integer assetId;
    private Integer employeeId;

    public AssetAllocation() {
        super();
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "AssetAllocation{" +
                "id=" + getId() +
                ", allocationDate=" + allocationDate +
                ", returnDate=" + returnDate +
                ", remarks='" + remarks + '\'' +
                ", assetId=" + assetId +
                ", employeeId=" + employeeId +
                '}';
    }
}