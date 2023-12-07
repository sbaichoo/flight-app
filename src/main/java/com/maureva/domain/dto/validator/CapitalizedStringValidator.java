package com.maureva.domain.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class CapitalizedStringValidator implements ConstraintValidator<Capitalized, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)) return true;
        return Character.isUpperCase(value.charAt(0));
    }

}