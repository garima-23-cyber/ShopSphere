package com.shopsphere.util;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            );

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("\\d{10}");

    public static boolean isValidEmail(String email) {

        return email != null &&
                EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {

        return phone != null &&
                PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isNonNegative(double value) {
        return value >= 0;
    }
}