package com.maureva.domain.entity;

import com.maureva.domain.dto.CabinClass;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "BOOKING_INFO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 993890623434L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "cabinClass", nullable = false)
    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass;

    @Column(name = "seatsAvailable", nullable = false)
    private Integer seatsAvailable = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_uuid")
    private Flight flight;

}
