package com.maureva.service;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.dto.AvailableFlightDto;
import com.maureva.domain.entity.AvailableFlight;
import com.maureva.domain.entity.DirectFlight;
import com.maureva.domain.entity.Flight;
import com.maureva.domain.entity.TransitFlight;
import com.maureva.mapper.AvailableFlightMapper;
import com.maureva.mapper.FlightMapper;
import com.maureva.repository.AvailableFlightRepository;
import com.maureva.repository.DirectFlightRepository;
import com.maureva.repository.FlightRepository;
import com.maureva.repository.TransitFlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableFlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    private final AvailableFlightRepository availableFlightRepository;

    private final AvailableFlightMapper availableFlightMapper;

    private final TransitFlightRepository transitFlightRepository;

    private final DirectFlightRepository directFlightRepository;

    public AvailableFlightDto findAvailableFlights(String origin, String destination) {
        AirportCode originAirportCode = AirportCode.valueOf(origin);
        AirportCode destinationAirportCode = AirportCode.valueOf(destination);

        return availableFlightRepository.findAvailableFlightByOriginAndDestination(originAirportCode, destinationAirportCode)
                .map(availableFlightMapper::map)
                .orElseGet(() -> searchAvailableFlights(origin, destination));
    }

    public AvailableFlightDto searchAvailableFlights(String origin, String destination) {
        AirportCode originAirportCode = AirportCode.valueOf(origin);
        AirportCode destinationAirportCode = AirportCode.valueOf(destination);

        AvailableFlight availableFlight = new AvailableFlight();
        availableFlight.setOrigin(originAirportCode);
        availableFlight.setDestination(destinationAirportCode);

        availableFlightRepository.save(availableFlight);

        List<TransitFlight> connectionPaths = findConnectionPaths(origin, destination)
                .stream()
                .map(transitFlight -> {
                    transitFlight.setAvailableFlight(availableFlight);
                    return transitFlightRepository.save(transitFlight);
                }).collect(Collectors.toList());

        List<DirectFlight> directAvailableFlights = findDirectAvailableFlights(origin, destination)
                .stream()
                .map(directFlight -> {
                    directFlight.setAvailableFlight(availableFlight);
                    return directFlightRepository.save(directFlight);
                }).collect(Collectors.toList());

        availableFlight.setTransitFlights(connectionPaths);
        availableFlight.setDirectFlights(directAvailableFlights);
        availableFlightRepository.save(availableFlight);

        return availableFlightMapper.map(availableFlight);
    }


    private List<DirectFlight> findDirectAvailableFlights(String origin, String destination) {
        return flightRepository.findFlightsByOriginAndDestination(AirportCode.valueOf(origin), AirportCode.valueOf(destination))
                .stream()
                .filter(flight -> flight.getBookingInfo().stream().anyMatch(bookingInfo -> bookingInfo.getSeatsAvailable() > 1))
                .map(flight -> {
                    AvailableFlightDto.DirectFlight directFlight = new AvailableFlightDto.DirectFlight(flightMapper.map(flight));
                    DirectFlight entityDirectFlight = availableFlightMapper.map(directFlight);

                    entityDirectFlight.setFlight(flight);

                    return directFlightRepository.findDirectFlightByKey(entityDirectFlight.getKey())
                            .orElse(directFlightRepository.save(entityDirectFlight));

                })
                .collect(Collectors.toList());
    }

    /*
     * Naive Solution
     * We assume that there can be only one connection flight
     * */
    private List<TransitFlight> findConnectionPaths(String origin, String destination) {
        return flightRepository.findConnectionPaths()
                .stream()
                .map(objects -> Arrays.stream(objects)
                        .map(object -> (Flight) object)
                        .collect(Collectors.toList()))
                .map(flights -> {
                            AvailableFlightDto.TransitFlight transitFlight = new AvailableFlightDto.TransitFlight(flightMapper.map(flights.get(0)), flightMapper.map(flights.get(1)));

                            if (transitFlight.isValid(origin, destination)) {
                                TransitFlight entityTransitFlight = availableFlightMapper.map(transitFlight);

                                entityTransitFlight.setFromTransit(flights.get(0));
                                entityTransitFlight.setToTransit(flights.get(1));

                                return transitFlightRepository.findTransitFlightByKey(entityTransitFlight.getKey())
                                        .orElse(transitFlightRepository.save(entityTransitFlight));

                            } else {
                                return null;
                            }
                        }
                ).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
