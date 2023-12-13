package com.maureva.rest;

import com.maureva.domain.dto.criteria.ReservationChoice;
import com.maureva.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Void> getAirportDto(@RequestBody ReservationChoice reservationChoice) {
        reservationService.bookFlight(reservationChoice);
        return ResponseEntity.noContent().build();
    }

}
