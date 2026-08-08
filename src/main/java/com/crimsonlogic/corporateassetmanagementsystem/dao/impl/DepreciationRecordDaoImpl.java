package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.DepreciationRecordDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.DepreciationRecord;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.DepreciationRecordMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepreciationRecordDaoImpl implements DepreciationRecordDao {

    @Override
    public void saveDepreciationRecord(DepreciationRecord record) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DepreciationRecordMapper mapper = session.getMapper(DepreciationRecordMapper.class);
            mapper.insertDepreciationRecord(record);
            session.commit();
        }
    }

    @Override
    public DepreciationRecord findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DepreciationRecordMapper mapper = session.getMapper(DepreciationRecordMapper.class);
            return mapper.getDepreciationRecordById(id);
        }
    }

    @Override
    public List<DepreciationRecord> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DepreciationRecordMapper mapper = session.getMapper(DepreciationRecordMapper.class);
            return mapper.getAllDepreciationRecords();
        }
    }

    @Override
    public List<DepreciationRecord> findByAssetId(Integer assetId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DepreciationRecordMapper mapper = session.getMapper(DepreciationRecordMapper.class);
            return mapper.getDepreciationRecordsByAssetId(assetId);
        }
    }
}