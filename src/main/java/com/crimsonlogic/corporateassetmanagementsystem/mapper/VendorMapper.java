package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Vendor;
import java.util.List;

public interface VendorMapper {
    void insertVendor(Vendor vendor);
    Vendor getVendorById(Integer id);
    List<Vendor> getAllVendors();
    void updateVendor(Vendor vendor);
    void deleteVendor(Integer id);
}