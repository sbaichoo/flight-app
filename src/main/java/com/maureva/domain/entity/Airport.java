package com.maureva.domain.entity;

import com.maureva.domain.dto.AirportCode;
import com.maureva.domain.dto.Country;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Entity
@Table(name = "AIRPORT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Airport {

    @Serial
    private static final long serialVersionUID = 5623890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "airportCode")
    private AirportCode airportCode;

}
