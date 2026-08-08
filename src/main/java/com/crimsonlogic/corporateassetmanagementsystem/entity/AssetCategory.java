package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;

public class AssetCategory extends BaseEntity {

    private String categoryName;
    private String description;

    public AssetCategory() {
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AssetCategory{" +
                "id=" + getId() +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}