package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetAllocationDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.AssetAllocationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AssetAllocationDaoImpl implements AssetAllocationDao {

    @Override
    public void saveAllocation(AssetAllocation allocation) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            mapper.insertAllocation(allocation);
            session.commit();
        }
    }

    @Override
    public AssetAllocation findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            return mapper.getAllocationById(id);
        }
    }

    @Override
    public List<AssetAllocation> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            return mapper.getAllAllocations();
        }
    }

    @Override
    public List<AssetAllocation> findByEmployeeId(Integer employeeId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            return mapper.getAllocationsByEmployeeId(employeeId);
        }
    }

    @Override
    public List<AssetAllocation> findByAssetId(Integer assetId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            return mapper.getAllocationsByAssetId(assetId);
        }
    }

    @Override
    public void updateAllocation(AssetAllocation allocation) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetAllocationMapper mapper = session.getMapper(AssetAllocationMapper.class);
            mapper.updateAllocation(allocation);
            session.commit();
        }
    }
}