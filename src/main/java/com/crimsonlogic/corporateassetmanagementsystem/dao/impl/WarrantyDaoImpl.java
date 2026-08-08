package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.WarrantyDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Warranty;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.WarrantyMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WarrantyDaoImpl implements WarrantyDao {

    @Override
    public void saveWarranty(Warranty warranty) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WarrantyMapper mapper = session.getMapper(WarrantyMapper.class);
            mapper.insertWarranty(warranty);
            session.commit();
        }
    }

    @Override
    public Warranty findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WarrantyMapper mapper = session.getMapper(WarrantyMapper.class);
            return mapper.getWarrantyById(id);
        }
    }

    @Override
    public Warranty findByAssetId(Integer assetId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WarrantyMapper mapper = session.getMapper(WarrantyMapper.class);
            return mapper.getWarrantyByAssetId(assetId);
        }
    }

    @Override
    public List<Warranty> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WarrantyMapper mapper = session.getMapper(WarrantyMapper.class);
            return mapper.getAllWarranties();
        }
    }

    @Override
    public void updateWarranty(Warranty warranty) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WarrantyMapper mapper = session.getMapper(WarrantyMapper.class);
            mapper.updateWarranty(warranty);
            session.commit();
        }
    }
}