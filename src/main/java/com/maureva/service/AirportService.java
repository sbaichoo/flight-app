package com.maureva.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.dto.AirportDto;
import com.maureva.mapper.AirportMapper;
import com.maureva.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    @Autowired
    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    public AirportDto getAirportDto(String airportCode) {
        return airportRepository.findAirportByAirportCode(AirportCode.valueOf(airportCode))
                .stream()
                .findFirst()
                .map(airportMapper::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<AirportDto> getAirportDtos() {
        return airportRepository.findAll()
                .stream()
                .map(airportMapper::map)
                .collect(Collectors.toList());

    }

    public void saveFlights(MultipartFile file) {
        CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, Boolean.TRUE);
        try {
            csvMapper.readerFor(AirportDto.class)
                    .with(orderLineSchema)
                    .readValues(file.getBytes())
                    .readAll()
                    .stream()
                    .filter(object -> object instanceof AirportDto)
                    .map(object -> (AirportDto) object)
                    .map(AirportDto::validate)
                    .filter(Objects::nonNull)
                    .map(airportMapper::map)
                    .forEach(airportRepository::save);

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
