package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import java.time.LocalDate;

public class DepreciationRecord extends BaseEntity {

    private Double depreciationAmount;
    private LocalDate calculationDate;

    // Foreign Key
    private Integer assetId;

    public DepreciationRecord() {
        super();
    }

    public Double getDepreciationAmount() {
        return depreciationAmount;
    }

    public void setDepreciationAmount(Double depreciationAmount) {
        this.depreciationAmount = depreciationAmount;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(LocalDate calculationDate) {
        this.calculationDate = calculationDate;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "DepreciationRecord{" +
                "id=" + getId() +
                ", depreciationAmount=" + depreciationAmount +
                ", calculationDate=" + calculationDate +
                ", assetId=" + assetId +
                '}';
    }
}