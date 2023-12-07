package com.maureva.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maureva.domain.dto.FlightDto;
import com.maureva.domain.dto.FlightsDtoDetails;
import com.maureva.mapper.FlightMapper;
import com.maureva.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.maureva.exception.ExceptionUtils.safelyTry;

@Service
@Validated
public class FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public void saveFlights(MultipartFile file) {
        readFlights(file)
                .stream()
                .map(flightMapper::map)
                .forEach(flightRepository::save);
    }

    private List<@Valid FlightDto> readFlights(MultipartFile file) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, Boolean.TRUE);
        return Optional.of(file)
                .map(safelyTry(MultipartFile::getBytes))
                .map(safelyTry(bytes -> xmlMapper.readValue(bytes, FlightsDtoDetails.class)))
                .map(FlightsDtoDetails::getFlights)
                .map(Arrays::stream)
                .stream()
                .flatMap(Function.identity())
                .map(FlightDto::validate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
