package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.MaintenanceRequestDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.MaintenanceRequestMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MaintenanceRequestDaoImpl implements MaintenanceRequestDao {

    @Override
    public void saveMaintenanceRequest(MaintenanceRequest request) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MaintenanceRequestMapper mapper = session.getMapper(MaintenanceRequestMapper.class);
            mapper.insertMaintenanceRequest(request);
            session.commit();
        }
    }

    @Override
    public MaintenanceRequest findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MaintenanceRequestMapper mapper = session.getMapper(MaintenanceRequestMapper.class);
            return mapper.getMaintenanceRequestById(id);
        }
    }

    @Override
    public List<MaintenanceRequest> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MaintenanceRequestMapper mapper = session.getMapper(MaintenanceRequestMapper.class);
            return mapper.getAllMaintenanceRequests();
        }
    }

    @Override
    public List<MaintenanceRequest> findByAssetId(Integer assetId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MaintenanceRequestMapper mapper = session.getMapper(MaintenanceRequestMapper.class);
            return mapper.getMaintenanceRequestsByAssetId(assetId);
        }
    }

    @Override
    public void updateMaintenanceRequest(MaintenanceRequest request) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MaintenanceRequestMapper mapper = session.getMapper(MaintenanceRequestMapper.class);
            mapper.updateMaintenanceRequest(request);
            session.commit();
        }
    }
}