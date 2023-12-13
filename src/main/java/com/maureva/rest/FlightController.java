package com.maureva.rest;

import com.maureva.domain.dto.AvailableFlightDto;
import com.maureva.service.AvailableFlightService;
import com.maureva.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    private final AvailableFlightService availableFlightService;

    @Autowired
    public FlightController(FlightService flightService, AvailableFlightService availableFlightService) {
        this.flightService = flightService;
        this.availableFlightService = availableFlightService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> uploadFlights(@RequestParam MultipartFile file) {
        flightService.saveFlights(file);
        return null;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<AvailableFlightDto> getAvailableFlight(@RequestParam String origin, @RequestParam String destination) {
        return ResponseEntity.ok(availableFlightService.findAvailableFlights(origin, destination));
    }

}
