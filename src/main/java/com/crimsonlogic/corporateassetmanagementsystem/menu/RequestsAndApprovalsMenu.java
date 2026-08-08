package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import com.crimsonlogic.corporateassetmanagementsystem.entity.MaintenanceRequest;
import com.crimsonlogic.corporateassetmanagementsystem.enums.RequestStatus;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.DisposalRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.MaintenanceRequestServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.DisposalRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.MaintenanceRequestService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;

import java.util.List;

public class RequestsAndApprovalsMenu {

    private final MaintenanceRequestService maintenanceService;
    private final DisposalRequestService disposalService;

    public RequestsAndApprovalsMenu() {
        this.maintenanceService = new MaintenanceRequestServiceImpl();
        this.disposalService = new DisposalRequestServiceImpl();
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("      REQUESTS & APPROVALS MODULE        ");
            System.out.println("=========================================");
            System.out.println("1. View Active Maintenance Requests");
            System.out.println("2. Complete a Maintenance Job");
            System.out.println("3. View Pending Disposal Requests");
            System.out.println("4. Approve/Reject Disposal Request");
            System.out.println("0. Back to Admin Dashboard");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleViewActiveMaintenance();
                    break;
                case 2:
                    handleCompleteMaintenance();
                    break;
                case 3:
                    handleViewPendingDisposals();
                    break;
                case 4:
                    handleProcessDisposal();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("[WARNING] Invalid choice. Please select a valid option.");
            }
        }
    }

    private void handleViewActiveMaintenance() {
        System.out.println("\n--- ACTIVE MAINTENANCE REQUESTS ---");
        try {
            List<MaintenanceRequest> requests = maintenanceService.getActiveMaintenanceRequests();

            if (requests.isEmpty()) {
                System.out.println("[INFO] There are no active maintenance requests at this time.");
                return;
            }

            for (MaintenanceRequest req : requests) {
                System.out.println("ID: " + req.getId() +
                        " | Asset ID: " + req.getAssetId() +
                        " | Status: " + req.getStatus() +
                        " | Issue: " + req.getIssueDescription());
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load maintenance requests: " + e.getMessage());
        }
    }

    private void handleCompleteMaintenance() {
        System.out.println("\n--- COMPLETE MAINTENANCE JOB ---");
        try {
            int maintenanceId = InputUtil.getInt("Enter the Maintenance Request ID to complete: ");
            double finalCost = InputUtil.getDouble("Enter the final repair cost: ");

            // The service updates the request to COMPLETED and changes the Asset back to AVAILABLE
            maintenanceService.completeMaintenance(maintenanceId, finalCost);
            System.out.println("[SUCCESS] Maintenance job marked as completed. Asset is now available.");

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to complete maintenance: " + e.getMessage());
        }
    }

    private void handleViewPendingDisposals() {
        System.out.println("\n--- PENDING DISPOSAL REQUESTS ---");
        try {
            List<DisposalRequest> requests = disposalService.getPendingRequests();

            if (requests.isEmpty()) {
                System.out.println("[INFO] There are no pending disposal requests at this time.");
                return;
            }

            for (DisposalRequest req : requests) {
                System.out.println("ID: " + req.getId() +
                        " | Asset ID: " + req.getAssetId() +
                        " | Requested By (Emp ID): " + req.getRequestedById() +
                        " | Reason: " + req.getDisposalReason());
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load disposal requests: " + e.getMessage());
        }
    }

    private void handleProcessDisposal() {
        System.out.println("\n--- PROCESS DISPOSAL REQUEST ---");
        try {
            int requestId = InputUtil.getInt("Enter the Disposal Request ID to process: ");

            System.out.println("1. Approve Disposal");
            System.out.println("2. Reject Disposal");
            int decision = InputUtil.getInt("Enter decision (1 or 2): ");

            if (decision == 1) {
                disposalService.processDisposalRequest(requestId, RequestStatus.APPROVED);
                System.out.println("[SUCCESS] Disposal request APPROVED. Asset status is now DISPOSED.");
            } else if (decision == 2) {
                disposalService.processDisposalRequest(requestId, RequestStatus.REJECTED);
                System.out.println("[SUCCESS] Disposal request REJECTED.");
            } else {
                System.out.println("[WARNING] Invalid decision. Action cancelled.");
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to process disposal request: " + e.getMessage());
        }
    }
}