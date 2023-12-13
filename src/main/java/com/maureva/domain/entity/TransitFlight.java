package com.maureva.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Transit_Flight")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransitFlight implements Serializable {

    @Serial
    private static final long serialVersionUID = 9562389523434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "key", updatable = false, nullable = false)
    private Integer key;

    @MapsId
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_uuid")
    private Flight toTransit;

    @MapsId
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_uuid")
    private Flight fromTransit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availableFlight_uuid")
    @ToString.Exclude
    private AvailableFlight availableFlight;

}
