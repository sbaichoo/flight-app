package com.maureva.repository;

import com.maureva.domain.entity.TransitFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransitFlightRepository extends JpaRepository<TransitFlight, UUID> {
    Optional<TransitFlight> findTransitFlightByKey(Integer key);

}
