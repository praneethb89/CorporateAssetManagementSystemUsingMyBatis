package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.AssetMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AssetDaoImpl implements AssetDao {

    @Override
    public void saveAsset(Asset asset) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetMapper mapper = session.getMapper(AssetMapper.class);
            mapper.insertAsset(asset);
            session.commit();
        }
    }

    @Override
    public Asset findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetMapper mapper = session.getMapper(AssetMapper.class);
            return mapper.getAssetById(id);
        }
    }

    @Override
    public List<Asset> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetMapper mapper = session.getMapper(AssetMapper.class);
            return mapper.getAllAssets();
        }
    }

    @Override
    public void updateAsset(Asset asset) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetMapper mapper = session.getMapper(AssetMapper.class);
            mapper.updateAsset(asset);
            session.commit();
        }
    }

    @Override
    public void deleteAsset(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetMapper mapper = session.getMapper(AssetMapper.class);
            mapper.deleteAsset(id);
            session.commit();
        }
    }
}