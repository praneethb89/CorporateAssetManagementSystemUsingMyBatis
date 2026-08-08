package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.AssetAllocationServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AssetAllocationService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

import java.util.List;

public class AllocationManagementMenu {

    private final AssetAllocationService allocationService;
    private final Employee adminUser;

    public AllocationManagementMenu(Employee adminUser) {
        this.allocationService = new AssetAllocationServiceImpl();
        this.adminUser = adminUser;
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("       ALLOCATION MANAGEMENT MODULE      ");
            System.out.println("=========================================");
            System.out.println("1. Allocate Asset to Employee");
            System.out.println("2. Return/Revoke Asset");
            System.out.println("3. View Active Allocations");
            System.out.println("0. Back to Admin Dashboard");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAllocateAsset();
                    break;
                case 2:
                    handleReturnAsset();
                    break;
                case 3:
                    handleViewActiveAllocations();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("[WARNING] Invalid choice. Please select a valid option.");
            }
        }
    }

    private void handleAllocateAsset() {
        System.out.println("\n--- ALLOCATE ASSET ---");
        try {
            int assetId = InputUtil.getInt("Enter Asset ID to allocate: ");
            int employeeId = InputUtil.getInt("Enter Employee ID receiving the asset: ");
            String notes = InputUtil.getString("Enter allocation notes: ");

            // Perfectly matching the required types: Integer, Integer, String, LocalDate
            allocationService.allocateAsset(assetId, employeeId, notes, java.time.LocalDate.now());

            System.out.println("[SUCCESS] Asset has been successfully allocated to the employee.");

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to allocate asset: " + e.getMessage());
        }
    }

    private void handleReturnAsset() {
        System.out.println("\n--- RETURN ASSET ---");
        try {
            int allocationId = InputUtil.getInt("Enter the Allocation ID to return: ");

            // Perfectly matching the required types: Integer, LocalDate
            allocationService.returnAsset(allocationId, java.time.LocalDate.now());

            System.out.println("[SUCCESS] Asset has been successfully returned and is now available.");

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to return asset: " + e.getMessage());
        }
    }

    private void handleViewActiveAllocations() {
        System.out.println("\n--- ACTIVE ALLOCATIONS ---");
        try {
            // FIXED: Using the exact method name from your interface
            List<AssetAllocation> allocations = allocationService.getAllActiveAllocations();

            if (allocations.isEmpty()) {
                System.out.println("[INFO] There are currently no active allocations.");
                return;
            }

            for (AssetAllocation allocation : allocations) {
                System.out.println("Allocation ID: " + allocation.getId() +
                        " | Asset ID: " + allocation.getAssetId() +
                        " | Employee ID: " + allocation.getEmployeeId() +
                        " | Date: " + allocation.getAllocationDate());
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error retrieving allocations: " + e.getMessage());
        }
    }
}