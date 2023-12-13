package com.maureva.repository;

import com.maureva.domain.entity.DirectFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DirectFlightRepository extends JpaRepository<DirectFlight, UUID> {
    Optional<DirectFlight> findDirectFlightByKey(Integer key);
}
