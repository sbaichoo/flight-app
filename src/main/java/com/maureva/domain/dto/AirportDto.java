package com.maureva.domain.dto;

import com.maureva.domain.dto.validator.Capitalized;
import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Slf4j
public class AirportDto {

    /*
    * TODO : Enhance with API to check if fields exist / is valid
    * */

    @NotNull
    private Country country;

    @Capitalized
    private String region;

    @NotBlank
    @Capitalized
    private String city;

    @NotNull
    private AirportCode airportCode;

    public AirportDto validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var constraintViolations = validator.validate(this);

        if (!CollectionUtils.isEmpty(constraintViolations)) {
            log.error(">>> ConstraintViolation Error for Airport : {}, fields {} , Exception: {}", this.airportCode,
                    constraintViolations
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(StringUtils.LF)),
                    ValidationException.class);
            return null;
        }

        return this;
    }

}
