package com.maureva.rest;

import com.maureva.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping (value = "/upload", consumes = {"multipart/form-data"})
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> uploadFlights(@RequestParam MultipartFile file) {
        flightService.saveFlights(file);
        return null;
    }

}
