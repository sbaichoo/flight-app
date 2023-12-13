package com.maureva.repository;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {

    List<Flight> findFlightsByOriginAndDestination(AirportCode origin, AirportCode destination);

    @Query("SELECT f1, f2 " +
            "FROM Flight f1 " +
            "JOIN Flight f2 ON f1.destination = f2.origin " +
            "WHERE f1.departureTime < f2.arrivalTime ")
    List<Object[]> findConnectionPaths();

}
