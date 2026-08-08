package com.crimsonlogic.corporateassetmanagementsystem.service.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.enums.Role;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuthenticationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DuplicateRecordException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;

import java.util.List;

public interface EmployeeService {
    // Core Business Logic
    Employee login(String email, String password) throws AuthenticationException;
    void registerEmployee(Employee employee) throws InvalidDataException, DuplicateRecordException;

    // Stream API & Fetch Logic
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByRole(Role role); // Filters using Streams
    List<Employee> getEmployeesSortedByDepartment(); // Sorts using Comparators
}