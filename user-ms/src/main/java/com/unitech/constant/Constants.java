package com.unitech.constant;

public class Constants {
    public static final String VALID_PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).{6,}$";
    public static final String VALID_PASSWORD_MESSAGE = "Your password include at " +
            "least one upper, one lower and one numeric character.";
}
