package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Vendor;
import java.util.List;

public interface VendorDao {
    void saveVendor(Vendor vendor);
    Vendor findById(Integer id);
    List<Vendor> findAll();
    void updateVendor(Vendor vendor);
    void deleteVendor(Integer id);
}