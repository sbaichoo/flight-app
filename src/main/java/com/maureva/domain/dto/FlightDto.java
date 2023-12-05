package com.maureva.domain.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@JacksonXmlRootElement(localName = "flight")
@ToString
public class FlightDto {

    @JacksonXmlProperty(localName = "Origin")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Carrier must contain exactly three uppercase letters")
    private String origin;

    @JacksonXmlProperty(localName = "Destination")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Carrier must contain exactly three uppercase letters")
    private String destination;

    @JacksonXmlProperty(localName = "DepartureTime")
    private String departureTime;

    @JacksonXmlProperty(localName = "FlightNumber")
    private Integer flightNumber;

    private String id;

    @JacksonXmlProperty(localName = "Carrier")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Carrier must contain exactly three uppercase letters")
    private String carrier;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "BookingInfo")
    @NotEmpty
    private List<@NotNull BookingInfo> bookingInfo;

    @JacksonXmlProperty(localName = "ArrivalTime")
    private String arrivalTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @Builder(toBuilder = true)
    @ToString
    @JacksonXmlRootElement(localName = "BookingInfo")
    public static class BookingInfo {

        @JacksonXmlProperty(localName = "CabinClass")
        private String cabinClass;

        @JacksonXmlProperty(localName = "SeatsAvailable")
        private Integer seatsAvailable;

    }

}

