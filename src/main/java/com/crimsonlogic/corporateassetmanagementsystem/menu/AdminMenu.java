package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

public class AdminMenu {

    private final Employee adminUser;

    public AdminMenu(Employee adminUser) {
        this.adminUser = adminUser;
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("          ADMINISTRATOR DASHBOARD        ");
            System.out.println("=========================================");
            // Utilizing our newly added name fields!
            System.out.println("Welcome, Admin: " + adminUser.getFirstName() + " " + adminUser.getLastName());
            System.out.println("1. Manage Assets");
            System.out.println("2. Manage Allocations");
            System.out.println("3. Manage Requests & Approvals");
            System.out.println("4. Reports & Analytics");
            System.out.println("0. Logout");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    // Routes to the new Asset Management module
                    new AssetManagementMenu().display();
                    break;
                case 2:
                    new AllocationManagementMenu(adminUser).display();
                    break;
                case 3:
                    new RequestsAndApprovalsMenu().display();
                    break;
                case 4:
                    new ReportsAndAnalyticsMenu().display();
                    break;
                case 0:
                    System.out.println("Logging out... returning to Portal Selection.");
                    return; // Breaks the loop and returns to AuthMenu
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}