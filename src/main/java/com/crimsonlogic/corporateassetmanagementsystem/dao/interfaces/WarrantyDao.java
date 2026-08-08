package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Warranty;
import java.util.List;

public interface WarrantyDao {
    void saveWarranty(Warranty warranty);
    Warranty findById(Integer id);
    Warranty findByAssetId(Integer assetId);
    List<Warranty> findAll();
    void updateWarranty(Warranty warranty);
}