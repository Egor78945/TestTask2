package com.example.library.service.employee.validation;

import com.example.library.model.dto.AuthenticationDTO;

public class EmployeeValidation {
    public static boolean isValid(AuthenticationDTO authenticationDTO) {
        return authenticationDTO.login().length() >= 5 &&
                authenticationDTO.login().length() <= 30 &&
                authenticationDTO.password().length() >= 5 &&
                authenticationDTO.password().length() <= 30 &&
                containsOnlyLetters(authenticationDTO.login()) &&
                containsOnlyLetters(authenticationDTO.password());
    }

    private static boolean containsOnlyLetters(String detail) {
        for (char i : detail.toCharArray()) {
            if (!(i >= 65 && i <= 90 || i >= 97 && i <= 122)) {
                return false;
            }
        }
        return true;
    }
}
