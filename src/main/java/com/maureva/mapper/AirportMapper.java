package com.maureva.mapper;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.dto.AirportDto;
import com.maureva.domain.entity.Airport;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirportMapper {

    Airport map(AirportDto airportDto);

    @Mapping(source = "airport.airportCode", target = "name", qualifiedByName = "mapToName")
    AirportDto map(Airport airport);

    @Named("mapToName")
    default String mapToName(AirportCode airportCode) {
        return airportCode.getAirportName();
    }

}
