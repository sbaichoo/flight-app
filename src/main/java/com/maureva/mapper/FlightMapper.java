package com.maureva.mapper;

import com.maureva.domain.dto.FlightDto;
import com.maureva.domain.entity.Flight;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightMapper {

    Flight map(FlightDto flightDto);

}
