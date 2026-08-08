package com.crimsonlogic.corporateassetmanagementsystem.validation;

import java.util.regex.Pattern;

public class DataValidator {

    /**
     * Regex for Indian Phone Numbers:
     * - Optional +91 or 91
     * - Optional 0 at the start
     * - Must be exactly 10 digits starting with 7, 8, or 9
     */
    private static final String INDIAN_PHONE_REGEX = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$";

    // Standard Email Regex
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Password requires at least 8 characters, 1 letter, and 1 number
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$";

    public static boolean isValidIndianPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(INDIAN_PHONE_REGEX).matcher(phoneNumber).matches();
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}