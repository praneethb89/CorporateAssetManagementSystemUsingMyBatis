package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;
import java.time.LocalDate;

public class Warranty extends BaseEntity {

    private String provider;
    private LocalDate expirationDate;

    // Foreign Key
    private Integer assetId;

    public Warranty() {
        super();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "id=" + getId() +
                ", provider='" + provider + '\'' +
                ", expirationDate=" + expirationDate +
                ", assetId=" + assetId +
                '}';
    }
}