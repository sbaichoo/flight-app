package com.maureva.domain.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@JacksonXmlRootElement(localName = "flights")
public class FlightsDtoDetails {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "flight")
    private FlightDto[] flights;
}
