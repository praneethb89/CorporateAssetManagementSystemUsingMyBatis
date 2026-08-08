package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.DisposalRequestDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.DisposalRequestMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DisposalRequestDaoImpl implements DisposalRequestDao {

    @Override
    public void saveDisposalRequest(DisposalRequest request) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DisposalRequestMapper mapper = session.getMapper(DisposalRequestMapper.class);
            mapper.insertDisposalRequest(request);
            session.commit();
        }
    }

    @Override
    public DisposalRequest findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DisposalRequestMapper mapper = session.getMapper(DisposalRequestMapper.class);
            return mapper.getDisposalRequestById(id);
        }
    }

    @Override
    public List<DisposalRequest> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DisposalRequestMapper mapper = session.getMapper(DisposalRequestMapper.class);
            return mapper.getAllDisposalRequests();
        }
    }

    @Override
    public void updateDisposalRequest(DisposalRequest request) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            DisposalRequestMapper mapper = session.getMapper(DisposalRequestMapper.class);
            mapper.updateDisposalRequest(request);
            session.commit();
        }
    }
}