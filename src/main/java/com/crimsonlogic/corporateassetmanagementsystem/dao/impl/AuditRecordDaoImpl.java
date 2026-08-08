package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AuditRecordDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.AuditRecord;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.AuditRecordMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AuditRecordDaoImpl implements AuditRecordDao {

    @Override
    public void saveAuditRecord(AuditRecord auditRecord) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AuditRecordMapper mapper = session.getMapper(AuditRecordMapper.class);
            mapper.insertAuditRecord(auditRecord);
            session.commit();
        }
    }

    @Override
    public AuditRecord findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AuditRecordMapper mapper = session.getMapper(AuditRecordMapper.class);
            return mapper.getAuditRecordById(id);
        }
    }

    @Override
    public List<AuditRecord> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AuditRecordMapper mapper = session.getMapper(AuditRecordMapper.class);
            return mapper.getAllAuditRecords();
        }
    }

    @Override
    public List<AuditRecord> findByAssetId(Integer assetId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AuditRecordMapper mapper = session.getMapper(AuditRecordMapper.class);
            return mapper.getAuditRecordsByAssetId(assetId);
        }
    }
}