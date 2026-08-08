package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import java.util.List;

public interface EmployeeMapper {
    void insertEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    Employee getEmployeeByEmployeeId(String employeeId);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
}