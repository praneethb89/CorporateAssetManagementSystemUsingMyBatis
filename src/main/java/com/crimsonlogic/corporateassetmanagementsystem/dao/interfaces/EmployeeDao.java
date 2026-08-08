package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void saveEmployee(Employee employee);
    Employee findById(Integer id);
    Employee findByEmployeeId(String employeeId);
    List<Employee> findAll();
    void updateEmployee(Employee employee);
}
