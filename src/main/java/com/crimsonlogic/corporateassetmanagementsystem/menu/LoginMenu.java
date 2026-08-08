package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.enums.Role;
import com.crimsonlogic.corporateassetmanagementsystem.exception.AuthenticationException;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.EmployeeServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.EmployeeService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

public class LoginMenu {

    private final EmployeeService employeeService;

    public LoginMenu() {
        this.employeeService = new EmployeeServiceImpl();
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("  CORPORATE ASSET MANAGEMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("0. Exit Application");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 0:
                    System.out.println("Exiting the system. Have a great day, boss!");
                    System.exit(0);
                    return; // Exits the method and stops the loop
                default:
                    System.out.println("Invalid choice. Please select 1 or 0.");
            }
        }
    }

    private void handleLogin() {
        System.out.println("\n--- USER LOGIN ---");
        String email = InputUtil.getString("Enter Email: ");
        String password = InputUtil.getString("Enter Password: ");

        try {
            // This will throw AuthenticationException if the credentials are bad
            Employee loggedInUser = employeeService.login(email, password);

            // 100% Safe fallback: Using getEmail() instead of guessing name fields
            System.out.println("\nLogin Successful! Welcome, " + loggedInUser.getEmail());

            // Routing logic is now completely uncommented and active!
            if (loggedInUser.getRole() == Role.ADMIN) {
                AdminMenu adminMenu = new AdminMenu(loggedInUser);
                adminMenu.display();
            } else {
                EmployeeMenu employeeMenu = new EmployeeMenu(loggedInUser);
                employeeMenu.display();
            }

        } catch (AuthenticationException e) {
            System.out.println(" " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}