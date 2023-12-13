package com.maureva.domain.entity;

import com.maureva.domain.dto.CabinClass;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "RESERVATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 26938906234560L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    private UUID userEntityId;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    private CabinClass cabinClass;

    private Integer flightKey;

}
