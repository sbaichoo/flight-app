package com.maureva.domain.entity;

import com.maureva.domain.dto.AirportCode;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
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
    private static final long serialVersionUID = 6893890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "origin", nullable = false)
    @Enumerated(EnumType.STRING)
    private AirportCode origin;

    @Column(name = "destination", nullable = false)
    @Enumerated(EnumType.STRING)
    private AirportCode destination;

    @Column(name = "departure_time", nullable = false)
    private OffsetDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private OffsetDateTime arrivalTime;

    @Column(name = "flight_number") // Customize length, name, etc., as needed
    private Integer flightNumber;

    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "carrier")
    private String carrier;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DirectFlight> directFlight;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingInfo> bookingInfo;

}
