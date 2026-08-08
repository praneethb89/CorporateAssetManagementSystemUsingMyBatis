package com.crimsonlogic.corporateassetmanagementsystem.menu;

import com.crimsonlogic.corporateassetmanagementsystem.entity.Asset;
import com.crimsonlogic.corporateassetmanagementsystem.enums.AssetStatus;
import com.crimsonlogic.corporateassetmanagementsystem.service.impl.AssetServiceImpl;
import com.crimsonlogic.corporateassetmanagementsystem.service.interfaces.AssetService;
import com.crimsonlogic.corporateassetmanagementsystem.util.InputUtil;
import com.crimsonlogic.corporateassetmanagementsystem.util.ConsoleTableUtil;

import java.util.ArrayList;
import java.util.List;

public class AssetManagementMenu {

    private final AssetService assetService;

    public AssetManagementMenu() {
        this.assetService = new AssetServiceImpl();
    }

    public void display() {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("         ASSET MANAGEMENT MODULE         ");
            System.out.println("=========================================");
            System.out.println("1. Add New Asset");
            System.out.println("2. View All Assets");
            System.out.println("3. Search Asset by ID");
            System.out.println("0. Back to Admin Dashboard");
            System.out.println("=========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddAsset();
                    break;
                case 2:
                    handleViewAllAssets();
                    break;
                case 3:
                    handleSearchAsset();
                    break;
                case 0:
                    return; // Takes the user back to AdminMenu
                default:
                    System.out.println("[WARNING] Invalid choice. Please select a valid option.");
            }
        }
    }

    private void handleAddAsset() {
        System.out.println("\n--- ADD NEW ASSET ---");
        try {
            Asset newAsset = new Asset();

            // Collect details from the admin
            newAsset.setAssetName(InputUtil.getString("Enter Asset Name (e.g., MacBook Pro): "));
            newAsset.setPurchaseCost(InputUtil.getDouble("Enter Purchase Cost: "));

            // A brand new asset entering the system is always AVAILABLE
            newAsset.setStatus(AssetStatus.AVAILABLE);

            assetService.addAsset(newAsset);
            System.out.println("[SUCCESS] Asset successfully added to the system!");

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to add asset: " + e.getMessage());
        }
    }

    private void handleViewAllAssets() {
        System.out.println("\n--- ALL ASSETS ---");
        try {
            List<Asset> assets = assetService.getAllAssets();
            if (assets.isEmpty()) {
                System.out.println("[INFO] No assets found in the system.");
                return;
            }

            // Upgraded to use our new ConsoleTableUtil!
            String[] headers = {"ID", "Name", "Cost ($)", "Status"};
            List<String[]> rows = new ArrayList<>();

            for (Asset asset : assets) {
                rows.add(new String[]{
                        String.valueOf(asset.getId()),
                        asset.getAssetName(),
                        String.valueOf(asset.getPurchaseCost()),
                        asset.getStatus().toString()
                });
            }

            ConsoleTableUtil.printTable(headers, rows);

        } catch (Exception e) {
            System.out.println("[ERROR] Error retrieving assets: " + e.getMessage());
        }
    }

    private void handleSearchAsset() {
        System.out.println("\n--- SEARCH ASSET ---");
        int assetId = InputUtil.getInt("Enter Asset ID to search: ");

        try {
            Asset asset = assetService.getAssetById(assetId);
            if (asset != null) {
                System.out.println("\n[SUCCESS] Asset Found:");
                System.out.println("ID: " + asset.getId());
                System.out.println("Name: " + asset.getAssetName());
                System.out.println("Cost: $" + asset.getPurchaseCost());
                System.out.println("Status: " + asset.getStatus());
            } else {
                System.out.println("[ERROR] Asset not found in the database.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Error searching for asset: " + e.getMessage());
        }
    }
}