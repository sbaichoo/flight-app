package com.maureva.domain.entity;

import com.maureva.domain.dto.AirportCode;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "AVAILABLE_FIGHT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AvailableFlight implements Serializable {

    @Serial
    private static final long serialVersionUID = 5623890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "origin", nullable = false)
    @Enumerated(EnumType.STRING)
    private AirportCode origin;

    @Column(name = "destination", nullable = false)
    @Enumerated(EnumType.STRING)
    private AirportCode destination;

    @OneToMany(mappedBy = "availableFlight")
    private List<DirectFlight> directFlights;

    @OneToMany(mappedBy = "availableFlight")
    private List<TransitFlight> transitFlights;

}
