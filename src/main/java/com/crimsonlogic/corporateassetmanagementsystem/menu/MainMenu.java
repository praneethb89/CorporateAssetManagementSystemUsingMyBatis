package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.enums.Role;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

public class MainMenu {

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("  CORPORATE ASSET MANAGEMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Admin Portal");
            System.out.println("2. Employee Portal");
            System.out.println("0. Exit Application");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    // Route to Auth menu, locking the role to ADMIN
                    new AuthMenu(Role.ADMIN).display();
                    break;
                case 2:
                    // Route to Auth menu, locking the role to EMPLOYEE
                    new AuthMenu(Role.EMPLOYEE).display();
                    break;
                case 0:
                    System.out.println("Exiting the system. Have a great day, boss!");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 0.");
            }
        }
    }
}