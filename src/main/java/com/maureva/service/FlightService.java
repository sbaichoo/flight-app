package com.maureva.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.maureva.domain.dto.FlightDto;
import com.maureva.domain.dto.FlightsDtoDetails;
import com.maureva.repository.FlightRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @SneakyThrows
    public void saveFlights(MultipartFile file) {
        readFlights(file).forEach(System.out::println);
        return;
    }

    private List<FlightDto> readFlights(MultipartFile file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        var flightsDetails = xmlMapper.readValue(file.getBytes(), FlightsDtoDetails.class);
        return List.of(flightsDetails.getFlights());
    }

    private void validateFlight(FlightDto flightDto) {

    }

}
