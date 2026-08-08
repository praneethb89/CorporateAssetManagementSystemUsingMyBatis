package com.crimsonlogic.corporateassetmanagementsystem.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a valid number.");
            }
        }
    }

    public static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public static String getString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("⚠️ Input cannot be empty. Please try again.");
        }
    }

    // For optional inputs like remarks or notes
    public static String getOptionalString(String prompt) {
        System.out.print(prompt + " (Press Enter to skip): ");
        return scanner.nextLine().trim();
    }
}