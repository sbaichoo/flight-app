package com.maureva.mapper;

import com.maureva.domain.dto.AvailableFlightDto;
import com.maureva.domain.entity.AvailableFlight;
import com.maureva.domain.entity.DirectFlight;
import com.maureva.domain.entity.TransitFlight;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvailableFlightMapper {

    AvailableFlight map(AvailableFlightDto availableFlightDto);

    @Mapping(source = "directFlights", target = "directFlights", qualifiedByName = "mapDirectFlights")
    AvailableFlightDto map(AvailableFlight availableFlight);

    @Named("mapDirectFlights")
    default List<AvailableFlightDto.DirectFlight> mapDirectFlights(List<DirectFlight> directFlights) {
        return directFlights
                .stream()
                .collect(Collectors.toMap(directFlight -> directFlight.getFlight().getId(), p -> p, (p, q) -> p))
                .values()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    DirectFlight map(AvailableFlightDto.DirectFlight directFlight);
    AvailableFlightDto.DirectFlight map(DirectFlight directFlight);

    TransitFlight map(AvailableFlightDto.TransitFlight transitFlight);
    AvailableFlightDto.TransitFlight map(TransitFlight transitFlight);

}
