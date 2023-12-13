package com.maureva.service;

import com.maureva.domain.dto.CabinClass;
import com.maureva.domain.dto.criteria.ReservationChoice;
import com.maureva.domain.entity.DirectFlight;
import com.maureva.domain.entity.Reservation;
import com.maureva.domain.entity.TransitFlight;
import com.maureva.repository.BookingInfoRepository;
import com.maureva.repository.DirectFlightRepository;
import com.maureva.repository.ReservationRepository;
import com.maureva.repository.TransitFlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final DirectFlightRepository directFlightRepository;

    private final TransitFlightRepository transitFlightRepository;

    private final BookingInfoRepository bookingInfoRepository;

    public void bookFlight(ReservationChoice reservationChoice) {
        directFlightRepository.findDirectFlightByKey(reservationChoice.key())
                .map(directFlight -> {
                    reserveDirectFlight(directFlight, reservationChoice.cabinClass());
                    return createReservation(reservationChoice);
                })
                .ifPresent(reservationRepository::save);

        transitFlightRepository.findTransitFlightByKey(reservationChoice.key())
                .map(transitFlight -> {
                    reserveTransitFlight(transitFlight, reservationChoice.cabinClass());
                    return createReservation(reservationChoice);
                }).ifPresent(reservationRepository::save);
    }

    private Reservation createReservation(ReservationChoice reservationChoice) {
        final var user = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        var builder = Reservation.builder()
                .userEntityId(UUID.fromString((String) user.getClaims().get("sub")))
                .email((String) user.getClaims().get("email"))
                .cabinClass(reservationChoice.cabinClass())
                .flightKey(reservationChoice.key())
                .name((String) user.getClaims().get("name"))
                .uuid(UUID.randomUUID());

        return reservationRepository.save(builder.build());
    }

    private void reserveDirectFlight(DirectFlight directFlight, CabinClass cabinClass) {
        directFlight.getFlight().getBookingInfo()
                .stream()
                .filter(bookingInfo -> bookingInfo.getCabinClass().equals(cabinClass))
                .findFirst()
                .stream()
                .peek(bookingInfo -> bookingInfo.setSeatsAvailable(bookingInfo.getSeatsAvailable() - 1))
                .forEach(bookingInfoRepository::save);
    }

    private void reserveTransitFlight(TransitFlight transitFlight, CabinClass cabinClass) {
        transitFlight.getFromTransit().getBookingInfo()
                .stream()
                .filter(bookingInfo -> bookingInfo.getCabinClass().equals(cabinClass))
                .findFirst()
                .stream()
                .peek(bookingInfo -> bookingInfo.setSeatsAvailable(bookingInfo.getSeatsAvailable() - 1))
                .forEach(bookingInfoRepository::save);
    }

}
