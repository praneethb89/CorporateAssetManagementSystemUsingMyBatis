package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.AssetAllocation;
import com.crimsonlogic.corporateassetmanagementsystem.entity.Employee;
import com.crimsonlogic.corporateassetmanagementsystem.enums.DisposalMethod;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.AssetAllocationServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.DisposalRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.MaintenanceRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AssetAllocationService;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DisposalRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.MaintenanceRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMenu {

    private final Employee employeeUser;
    private final AssetAllocationService allocationService;
    private final MaintenanceRequestService maintenanceService;
    private final DisposalRequestService disposalService;

    public EmployeeMenu(Employee employeeUser) {
        this.employeeUser = employeeUser;
        this.allocationService = new AssetAllocationServiceImpl();
        this.maintenanceService = new MaintenanceRequestServiceImpl();
        this.disposalService = new DisposalRequestServiceImpl();
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("             EMPLOYEE PORTAL             ");
            System.out.println("=========================================");
            System.out.println("Welcome, " + employeeUser.getFirstName() + " " + employeeUser.getLastName());
            System.out.println("1. View My Allocated Assets");
            System.out.println("2. Report an Issue (Maintenance Request)");
            System.out.println("3. Request Asset Disposal");
            System.out.println("0. Logout");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleViewMyAssets();
                    break;
                case 2:
                    handleRequestMaintenance();
                    break;
                case 3:
                    handleRequestDisposal();
                    break;
                case 0:
                    System.out.println("Logging out... returning to Portal Selection.");
                    return; // Breaks the loop and returns to AuthMenu
                default:
                    System.out.println("[WARNING] Invalid choice. Please select a valid option.");
            }
        }
    }

    private void handleViewMyAssets() {
        System.out.println("\n--- MY ALLOCATED ASSETS ---");
        try {
            // FIXED: Using the exact method name from your interface
            List<AssetAllocation> myAllocations = allocationService.getAllActiveAllocations().stream()
                    .filter(alloc -> alloc.getEmployeeId().equals(employeeUser.getId()))
                    .collect(Collectors.toList());

            if (myAllocations.isEmpty()) {
                System.out.println("[INFO] You currently have no assets allocated to you.");
                return;
            }

            for (AssetAllocation allocation : myAllocations) {
                System.out.println("Allocation ID: " + allocation.getId() +
                        " | Asset ID: " + allocation.getAssetId() +
                        " | Allocated On: " + allocation.getAllocationDate());
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to retrieve your assets: " + e.getMessage());
        }
    }

    private void handleRequestMaintenance() {
        System.out.println("\n--- SUBMIT MAINTENANCE REQUEST ---");
        try {
            int assetId = InputUtil.getInt("Enter the Asset ID requiring maintenance: ");
            String issueDescription = InputUtil.getString("Describe the issue in detail: ");

            // For an employee request, estimated cost might be unknown initially, so we can default to 0.0
            double estimatedCost = 0.0;

            maintenanceService.scheduleMaintenance(assetId, issueDescription, estimatedCost);
            System.out.println("[SUCCESS] Maintenance request submitted successfully. An admin will review it.");
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to submit maintenance request: " + e.getMessage());
        }
    }

    private void handleRequestDisposal() {
        System.out.println("\n--- SUBMIT DISPOSAL REQUEST ---");
        try {
            int assetId = InputUtil.getInt("Enter the Asset ID to be disposed: ");
            String reason = InputUtil.getString("Enter the reason for disposal (e.g., Broken beyond repair, Obsolete): ");

            System.out.println("Select Proposed Disposal Method:");
            System.out.println("1. SCRAPPED");
            System.out.println("2. DONATED");
            System.out.println("3. SOLD");
            int methodChoice = InputUtil.getInt("Choice: ");

            DisposalMethod method = DisposalMethod.SCRAPPED;
            if (methodChoice == 2) method = DisposalMethod.DONATED;
            if (methodChoice == 3) method = DisposalMethod.SOLD;

            double estimatedValue = 0.0;
            if (method == DisposalMethod.SOLD) {
                estimatedValue = InputUtil.getDouble("Enter estimated sale value: ");
            }

            disposalService.submitDisposalRequest(assetId, employeeUser.getId(), reason, method, estimatedValue);
            System.out.println("[SUCCESS] Disposal request submitted successfully. Awaiting Admin approval.");
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to submit disposal request: " + e.getMessage());
        }
    }
}