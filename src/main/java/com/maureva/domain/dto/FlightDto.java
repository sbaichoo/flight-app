package com.maureva.domain.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.maureva.domain.adapter.CabinClassAdapter;
import com.maureva.domain.adapter.OffsetDateTimeAdapter;
import com.maureva.domain.dto.validator.ValidFlightDto;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@JacksonXmlRootElement(localName = "flight")
@ValidFlightDto
@Slf4j
public class FlightDto {

    @JacksonXmlProperty(localName = "Origin")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Origin must contain exactly three uppercase letters")
    private String origin;

    @JacksonXmlProperty(localName = "Destination")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Destination must contain exactly three uppercase letters")
    private String destination;

    @JacksonXmlProperty(localName = "DepartureTime")
    @NotNull
    private OffsetDateTime departureTime;

    @JacksonXmlProperty(localName = "ArrivalTime")
    @NotNull
    private OffsetDateTime arrivalTime;

    @JacksonXmlProperty(localName = "FlightNumber")
    @NotNull
    private Integer flightNumber;

    @NotBlank
    private String id;

    @JacksonXmlProperty(localName = "Carrier")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Carrier must contain exactly three uppercase letters")
    private String carrier;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "BookingInfo")
    @NotEmpty
    private List<@NotNull BookingInfo> bookingInfo;

    @XmlJavaTypeAdapter(value = OffsetDateTimeAdapter.class)
    public OffsetDateTime getDepartureTime() {
        return departureTime;
    }

    @XmlJavaTypeAdapter(value = OffsetDateTimeAdapter.class)
    public OffsetDateTime getArrivalTime() {
        return arrivalTime;
    }

    public FlightDto validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var flightConstraintViolations = validator.validate(this);
        var bookingInfoConstraintViolations = validator.validate(this.bookingInfo);

        if (!CollectionUtils.isEmpty(flightConstraintViolations)) {
            log.error(">>> ConstraintViolation Error for Flight : {}, fields {} , Exception: {}", this.flightNumber,
                    Stream.of(flightConstraintViolations, bookingInfoConstraintViolations)
                            .flatMap(Collection::stream)
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(StringUtils.LF)),
                    ValidationException.class);
            return null;
        }

        return this;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder(toBuilder = true)
    @Slf4j
    @JacksonXmlRootElement(localName = "BookingInfo")
    public static class BookingInfo {

        @JacksonXmlProperty(localName = "CabinClass")
        @NotNull
        private CabinClass cabinClass;

        @JacksonXmlProperty(localName = "SeatsAvailable")
        @NotNull
        @Positive
        private Integer seatsAvailable;

        @XmlJavaTypeAdapter(value = CabinClassAdapter.class)
        public CabinClass getCabinClass() {
            return cabinClass;
        }

    }

}

