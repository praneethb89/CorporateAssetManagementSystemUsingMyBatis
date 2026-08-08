package com.crimsonlogic.corporateassetmanagementsystem.entity;

import com.crimsonlogic.corporateassetmanagementsystem.entity.abstracts.BaseEntity;

public class Vendor extends BaseEntity {

    private String vendorName;
    private String contactEmail;

    public Vendor() {
        super();
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + getId() +
                ", vendorName='" + vendorName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }
}