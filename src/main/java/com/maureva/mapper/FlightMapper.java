package com.maureva.mapper;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.dto.FlightDto;
import com.maureva.domain.entity.Flight;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightMapper {

    Flight map(FlightDto flightDto);

    @Mapping(target = "originAirport", source = "flight.origin", qualifiedByName = "mapToName")
    @Mapping(target = "destinationAirport", source = "flight.destination", qualifiedByName = "mapToName")
    FlightDto map(Flight flight);

    @Named("mapToName")
    default String mapToName(AirportCode airportCode) {
        return airportCode.getAirportName();
    }

}
