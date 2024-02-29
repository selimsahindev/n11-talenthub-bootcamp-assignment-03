package com.selimsahin.homework03.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author selimsahindev
 */
@Component
public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {
    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // Check if the value is null or empty
        if (!StringUtils.hasText(value)) {
            return false;
        }

        // Remove whitespace and check if all characters are letters
        return value.chars().allMatch(Character::isLetter);
    }
}
