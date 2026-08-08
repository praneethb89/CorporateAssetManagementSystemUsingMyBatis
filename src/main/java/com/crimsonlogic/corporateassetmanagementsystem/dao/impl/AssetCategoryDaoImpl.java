package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.AssetCategoryDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetCategory;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.AssetCategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AssetCategoryDaoImpl implements AssetCategoryDao {

    @Override
    public void saveAssetCategory(AssetCategory category) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetCategoryMapper mapper = session.getMapper(AssetCategoryMapper.class);
            mapper.insertAssetCategory(category);
            session.commit();
        }
    }

    @Override
    public AssetCategory findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetCategoryMapper mapper = session.getMapper(AssetCategoryMapper.class);
            return mapper.getAssetCategoryById(id);
        }
    }

    @Override
    public List<AssetCategory> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetCategoryMapper mapper = session.getMapper(AssetCategoryMapper.class);
            return mapper.getAllAssetCategories();
        }
    }

    @Override
    public void updateAssetCategory(AssetCategory category) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetCategoryMapper mapper = session.getMapper(AssetCategoryMapper.class);
            mapper.updateAssetCategory(category);
            session.commit();
        }
    }

    @Override
    public void deleteAssetCategory(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AssetCategoryMapper mapper = session.getMapper(AssetCategoryMapper.class);
            mapper.deleteAssetCategory(id);
            session.commit();
        }
    }
}