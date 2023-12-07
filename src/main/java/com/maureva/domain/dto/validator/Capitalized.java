package com.maureva.domain.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CapitalizedStringValidator.class)
public @interface Capitalized {

    String message() default "String must start with a capital letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
