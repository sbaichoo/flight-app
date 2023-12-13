package com.maureva.rest;

import com.maureva.domain.dto.AirportDto;
import com.maureva.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> uploadAirports(@RequestParam MultipartFile file) {
        airportService.saveFlights(file);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{airportCode}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<AirportDto> getAirportDto(@PathVariable String airportCode) {
        return ResponseEntity.ok(airportService.getAirportDto(airportCode));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<List<AirportDto>> getAirportDtos() {
        return ResponseEntity.ok(airportService.getAirportDtos());
    }

}
