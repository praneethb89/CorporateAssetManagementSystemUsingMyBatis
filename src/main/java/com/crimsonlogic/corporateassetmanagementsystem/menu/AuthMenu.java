package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.enums.Role;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuthenticationException;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.EmployeeServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.EmployeeService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

public class AuthMenu {

    private final EmployeeService employeeService;
    private final Role portalRole;

    // Constructor accepts the Role so we know which portal we are in
    public AuthMenu(Role portalRole) {
        this.employeeService = new EmployeeServiceImpl();
        this.portalRole = portalRole;
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("       " + portalRole + " PORTAL");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Back to Main Menu");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegister();
                    break;
                case 0:
                    return; // Breaks this loop and goes back to MainMenu
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 0.");
            }
        }
    }

    private void handleLogin() {
        System.out.println("\n--- " + portalRole + " LOGIN ---");
        String email = InputUtil.getString("Enter Email: ");
        String password = InputUtil.getString("Enter Password: ");

        try {
            Employee loggedInUser = employeeService.login(email, password);

            // Verify they are logging into the correct portal
            if (loggedInUser.getRole() != portalRole) {
                System.out.println("Access Denied: You are registered as an " + loggedInUser.getRole() +
                        ", but you are trying to access the " + portalRole + " portal.");
                return;
            }

            System.out.println("\nLogin Successful! Welcome, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

            if (loggedInUser.getRole() == Role.ADMIN) {
                new AdminMenu(loggedInUser).display();
            } else {
                new EmployeeMenu(loggedInUser).display();
            }

        } catch (AuthenticationException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ An unexpected error occurred: " + e.getMessage());
        }
    }

    private void handleRegister() {
        System.out.println("\n--- " + portalRole + " REGISTRATION ---");

        Employee newEmployee = new Employee();

        // Now we capture the first and last name!
        newEmployee.setFirstName(InputUtil.getString("Enter First Name: "));
        newEmployee.setLastName(InputUtil.getString("Enter Last Name: "));

        newEmployee.setEmail(InputUtil.getString("Enter Email: "));
        newEmployee.setPassword(InputUtil.getString("Enter Password: "));
        newEmployee.setDepartment(InputUtil.getString("Enter Department: "));

        newEmployee.setRole(portalRole);

        try {
            employeeService.registerEmployee(newEmployee);
            System.out.println("\nRegistration Successful! You can now log in.");
        } catch (Exception e) {
            System.out.println("Registration Failed: " + e.getMessage());
        }
    }
}