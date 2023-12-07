package com.maureva.domain.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFlightDtoValidator.class)
@Documented
public @interface ValidFlightDto {

    String message() default "Arrival time must be after departure time Or Origin must not be the same as Destination";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}