package com.maureva.repository;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.entity.AvailableFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailableFlightRepository extends JpaRepository<AvailableFlight, UUID> {
    Optional<AvailableFlight> findAvailableFlightByOriginAndDestination(AirportCode origin, AirportCode destination);
}
