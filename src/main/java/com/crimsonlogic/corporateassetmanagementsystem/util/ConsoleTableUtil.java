package com.crimsonlogic.corporateassetmanagementsystem.util;

import java.util.List;

public class ConsoleTableUtil {

    public static void printTable(String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) {
            System.out.println("[ERROR] No headers provided for table.");
            return;
        }

        // Calculate the maximum width required for each column
        int[] columnWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null && row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Print the table structure
        printSeparator(columnWidths);
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        if (rows.isEmpty()) {
            System.out.println("| NO DATA AVAILABLE " + getPadding(calculateTotalWidth(columnWidths) - 18) + "|");
        } else {
            for (String[] row : rows) {
                printRow(row, columnWidths);
            }
        }
        printSeparator(columnWidths);
    }

    private static void printRow(String[] row, int[] columnWidths) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < row.length; i++) {
            String value = row[i] != null ? row[i] : "";
            // Pad the string to the calculated column width
            sb.append(String.format(" %-" + columnWidths[i] + "s |", value));
        }
        System.out.println(sb.toString());
    }

    private static void printSeparator(int[] columnWidths) {
        StringBuilder sb = new StringBuilder("+");
        for (int width : columnWidths) {
            for (int i = 0; i < width + 2; i++) {
                sb.append("-");
            }
            sb.append("+");
        }
        System.out.println(sb.toString());
    }

    private static int calculateTotalWidth(int[] columnWidths) {
        int total = 0;
        for (int width : columnWidths) {
            total += width + 3;
        }
        return total;
    }

    private static String getPadding(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}