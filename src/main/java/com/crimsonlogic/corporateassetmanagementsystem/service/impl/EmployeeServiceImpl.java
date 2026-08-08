package com.crimsonlogic.corporateassetmanagementsystem.service.impl;

import com.crimsonlogic.corporateassetmanagementsystem.dao.impl.EmployeeDaoImpl;
import com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces.EmployeeDao;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.enums.Role;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuthenticationException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.DuplicateRecordException;
import com.crimsonlogic.corporateassetmanagementsystem.exception.InvalidDataException;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.EmployeeService;
import com.crimsonlogic.corporateassetmanagementsystem.validation.DataValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    // Dependency Injection of the DAO
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public Employee login(String email, String password) throws AuthenticationException {
        // Using Java 8 Streams and Optional to find the matching user securely
        List<Employee> employees = employeeDao.findAll();

        Optional<Employee> loggedInUser = employees.stream()
                .filter(emp -> emp.getEmail().equalsIgnoreCase(email) && emp.getPassword().equals(password))
                .findFirst();

        // If Optional is empty, it throws the exception. If present, returns the user.
        return loggedInUser.orElseThrow(() ->
                new AuthenticationException("Invalid email or password. Please try again."));
    }

    @Override
    public void registerEmployee(Employee employee) throws InvalidDataException, DuplicateRecordException {
        // 1. Validation using our utility
        if (!DataValidator.isValidEmail(employee.getEmail())) {
            throw new InvalidDataException("Invalid Email format.");
        }
        if (!DataValidator.isValidPassword(employee.getPassword())) {
            throw new InvalidDataException("Password must be at least 8 characters, with 1 letter and 1 number.");
        }

        // 2. Check for Duplicate Email using Streams
        List<Employee> existingEmployees = employeeDao.findAll();
        boolean emailExists = existingEmployees.stream()
                .anyMatch(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail()));

        if (emailExists) {
            throw new DuplicateRecordException("An employee with this email already exists.");
        }

        // 3. Auto-generate the Business ID (EMP001, EMP002, etc.)
        int currentCount = existingEmployees.size();
        String generatedId = String.format("EMP%03d", currentCount + 1);
        employee.setEmployeeId(generatedId);

        // Default role if not set
        if (employee.getRole() == null) {
            employee.setRole(Role.EMPLOYEE);
        }

        // 4. Save to Database
        employeeDao.saveEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> getEmployeesByRole(Role role) {
        // Using Stream API to filter by role (Requirement!)
        return employeeDao.findAll().stream()
                .filter(emp -> emp.getRole() == role)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeesSortedByDepartment() {
        // Using Stream API and Comparator to sort alphabetically, handling nulls gracefully
        return employeeDao.findAll().stream()
                .sorted(Comparator.comparing(Employee::getDepartment, Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toList());
    }
}