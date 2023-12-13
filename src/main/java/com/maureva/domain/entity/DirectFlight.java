package com.maureva.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "DIRECT_FIGHT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DirectFlight implements Serializable {

    @Serial
    private static final long serialVersionUID = 213890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "key", updatable = false, nullable = false)
    private Integer key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_uuid")
    @ToString.Exclude
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availableFlight_uuid")
    @ToString.Exclude
    private AvailableFlight availableFlight;

}
