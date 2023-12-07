package com.maureva.domain.dto.validator;

import com.maureva.domain.dto.FlightDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ValidFlightDtoValidator implements ConstraintValidator<ValidFlightDto, FlightDto> {
    @Override
    public boolean isValid(FlightDto flightDto, ConstraintValidatorContext constraintValidatorContext) {
        return flightDto.getArrivalTime().isAfter(flightDto.getDepartureTime()) &&
                !Objects.equals(flightDto.getOrigin(), flightDto.getDestination());
    }

}