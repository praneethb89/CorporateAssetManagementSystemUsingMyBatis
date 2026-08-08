package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DepreciationRecord;
import java.util.List;

public interface DepreciationRecordMapper {
    void insertDepreciationRecord(DepreciationRecord record);
    DepreciationRecord getDepreciationRecordById(Integer id);
    List<DepreciationRecord> getAllDepreciationRecords();
    List<DepreciationRecord> getDepreciationRecordsByAssetId(Integer assetId);
}