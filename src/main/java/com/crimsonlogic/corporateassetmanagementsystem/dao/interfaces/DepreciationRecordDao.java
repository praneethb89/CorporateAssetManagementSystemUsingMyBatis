package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DepreciationRecord;
import java.util.List;

public interface DepreciationRecordDao {
    void saveDepreciationRecord(DepreciationRecord record);
    DepreciationRecord findById(Integer id);
    List<DepreciationRecord> findAll();
    List<DepreciationRecord> findByAssetId(Integer assetId);
}