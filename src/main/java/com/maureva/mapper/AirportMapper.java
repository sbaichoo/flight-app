package com.maureva.mapper;

import com.maureva.domain.dto.AirportDto;
import com.maureva.domain.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirportMapper {

    Airport map(AirportDto airportDto);

    AirportDto map(Airport airport);

}
