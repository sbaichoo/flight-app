package com.maureva.domain.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "FLIGHT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Flight implements Serializable {

    @Serial
    private static final long serialVersionUID = 5623890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @Column(name = "flight_number") // Customize length, name, etc., as needed
    private Integer flightNumber;

    @Column(name = "flight_id", length = 50, unique = true)
    private String id;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "arrival_time", nullable = false)
    private String arrivalTime;

}
