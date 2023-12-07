package com.maureva.repository;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {

    Optional<Airport> findAirportByAirportCode(AirportCode airportCode);

}
