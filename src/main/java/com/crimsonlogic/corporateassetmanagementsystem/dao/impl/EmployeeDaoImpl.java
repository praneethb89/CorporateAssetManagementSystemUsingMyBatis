package com.crimsonlogic.corporateassetmanagementsystem.dao.impl;

import com.crimsonlogic.corporateassetmanagementsystem.config.MyBatisUtil;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.EmployeeDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void saveEmployee(Employee employee) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            mapper.insertEmployee(employee);
            session.commit();
        }
    }

    @Override
    public Employee findById(Integer id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            return mapper.getEmployeeById(id);
        }
    }

    @Override
    public Employee findByEmployeeId(String employeeId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            return mapper.getEmployeeByEmployeeId(employeeId);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            return mapper.getAllEmployees();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            mapper.updateEmployee(employee);
            session.commit();
        }
    }
}