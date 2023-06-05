package com.example.authorization_service.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean containsNumbers = false;
        boolean containsUpperCaseLetters = false;
        boolean containsLowerCaseLetters = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                containsNumbers = true;
            }
            if (Character.isUpperCase(s.charAt(i))) {
                containsUpperCaseLetters = true;
            }
            if (Character.isLowerCase(s.charAt(i))) {
                containsLowerCaseLetters = true;
            }
        }
        return containsNumbers && containsUpperCaseLetters && containsLowerCaseLetters;
    }
}
