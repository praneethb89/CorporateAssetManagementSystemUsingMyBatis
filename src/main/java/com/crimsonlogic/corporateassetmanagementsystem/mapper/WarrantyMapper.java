package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Warranty;
import java.util.List;

public interface WarrantyMapper {
    void insertWarranty(Warranty warranty);
    Warranty getWarrantyById(Integer id);
    Warranty getWarrantyByAssetId(Integer assetId);
    List<Warranty> getAllWarranties();
    void updateWarranty(Warranty warranty);
}