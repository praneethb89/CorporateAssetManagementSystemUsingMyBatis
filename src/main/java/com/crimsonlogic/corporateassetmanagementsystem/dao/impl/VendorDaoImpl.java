package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.VendorDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Vendor;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.VendorMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class VendorDaoImpl implements VendorDao {

    @Override
    public void saveVendor(Vendor vendor) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VendorMapper mapper = session.getMapper(VendorMapper.class);
            mapper.insertVendor(vendor);
            session.commit();
        }
    }

    @Override
    public Vendor findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VendorMapper mapper = session.getMapper(VendorMapper.class);
            return mapper.getVendorById(id);
        }
    }

    @Override
    public List<Vendor> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VendorMapper mapper = session.getMapper(VendorMapper.class);
            return mapper.getAllVendors();
        }
    }

    @Override
    public void updateVendor(Vendor vendor) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VendorMapper mapper = session.getMapper(VendorMapper.class);
            mapper.updateVendor(vendor);
            session.commit();
        }
    }

    @Override
    public void deleteVendor(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VendorMapper mapper = session.getMapper(VendorMapper.class);
            mapper.deleteVendor(id);
            session.commit();
        }
    }
}