package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.DepreciationRecordServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.DisposalRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.MaintenanceRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DepreciationRecordService;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DisposalRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.MaintenanceRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

import java.util.Optional;
import java.util.OptionalDouble;

public class ReportsAndAnalyticsMenu {

    private final MaintenanceRequestService maintenanceService;
    private final DisposalRequestService disposalService;
    private final DepreciationRecordService depreciationService;

    public ReportsAndAnalyticsMenu() {
        this.maintenanceService = new MaintenanceRequestServiceImpl();
        this.disposalService = new DisposalRequestServiceImpl();
        this.depreciationService = new DepreciationRecordServiceImpl();
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("      REPORTS & ANALYTICS DASHBOARD      ");
            System.out.println("=========================================");
            System.out.println("1. Financial: Total Recovered Value from Disposals");
            System.out.println("2. Financial: Average Asset Depreciation");
            System.out.println("3. Maintenance: Costliest Repair Job");
            System.out.println("0. Back to Admin Dashboard");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleTotalRecoveredValue();
                    break;
                case 2:
                    handleAverageDepreciation();
                    break;
                case 3:
                    handleCostliestRepair();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("[WARNING] Invalid choice. Please select a valid option.");
            }
        }
    }

    private void handleTotalRecoveredValue() {
        System.out.println("\n--- TOTAL RECOVERED VALUE (DISPOSALS) ---");
        try {
            // Utilizing Java 8 Stream API from DisposalRequestService
            double totalValue = disposalService.getTotalRecoveredValue();
            System.out.println("[INFO] The total financial value recovered from approved asset disposals is: $" + totalValue);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to generate report: " + e.getMessage());
        }
    }

    private void handleAverageDepreciation() {
        System.out.println("\n--- AVERAGE ASSET DEPRECIATION ---");
        try {
            // Utilizing Java 8 Stream API OptionalDouble from DepreciationRecordService
            OptionalDouble averageOpt = depreciationService.getAverageDepreciationAmount();

            if (averageOpt.isPresent()) {
                System.out.println("[INFO] The average depreciation amount recorded across all assets is: $" + averageOpt.getAsDouble());
            } else {
                System.out.println("[INFO] No depreciation records currently exist to calculate an average.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to generate report: " + e.getMessage());
        }
    }

    private void handleCostliestRepair() {
        System.out.println("\n--- COSTLIEST MAINTENANCE RECORD ---");
        try {
            // Utilizing Java 8 Stream API Optional from MaintenanceRequestService
            Optional<MaintenanceRequest> costliestOpt = maintenanceService.getCostliestMaintenanceRecord();

            if (costliestOpt.isPresent()) {
                MaintenanceRequest req = costliestOpt.get();
                System.out.println("[INFO] Costliest Repair Found:");
                System.out.println("Maintenance ID: " + req.getId());
                System.out.println("Asset ID: " + req.getAssetId());
                System.out.println("Issue: " + req.getIssueDescription());
                System.out.println("Cost: $" + req.getCost());
                System.out.println("Status: " + req.getStatus());
            } else {
                System.out.println("[INFO] No maintenance records with costs were found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to generate report: " + e.getMessage());
        }
    }
}