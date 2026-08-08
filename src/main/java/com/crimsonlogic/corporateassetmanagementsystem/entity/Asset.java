package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;

import java.time.LocalDate;

public class Asset extends BaseEntity {

    private String assetName; // Updated based on trainer's feedback
    private AssetStatus status;    // e.g., Available, Allocated, Maintenance, Disposed
    private Double purchaseCost;
    private LocalDate purchaseDate;

    // Foreign Keys / Associations for MyBatis mapping
    private Integer categoryId;
    private Integer vendorId;

    public Asset() {
        super();
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + getId() +
                ", assetName='" + assetName + '\'' +
                ", status='" + status + '\'' +
                ", purchaseCost=" + purchaseCost +
                ", purchaseDate=" + purchaseDate +
                ", categoryId=" + categoryId +
                ", vendorId=" + vendorId +
                '}';
    }
}