package com.maureva.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class AvailableFlightDto {

    private static Long CONNECTION_FLIGHT_DURATION = 2L;

    private AirportCode origin;

    private AirportCode destination;

    private List<DirectFlight> directFlights;

    private List<TransitFlight> transitFlights;

    @Data
    @NoArgsConstructor
    public static class DirectFlight {
        private Integer key;

        private FlightDto flight;

        public DirectFlight(FlightDto flight) {
            this.flight = flight;
            this.key = hashCode();
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (flight != null ? flight.hashCode() : 0);
            return Math.abs(result);
        }
    }

    @Data
    @NoArgsConstructor
    public static class TransitFlight {
        private Integer key;

        private FlightDto toTransit;

        private FlightDto fromTransit;

        public TransitFlight(FlightDto toTransit, FlightDto fromTransit) {
            this.toTransit = toTransit;
            this.fromTransit = fromTransit;
            this.key = hashCode();
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (toTransit != null ? toTransit.hashCode() : 0);
            result = 31 * result + (fromTransit != null ? fromTransit.hashCode() : 0);
            return Math.abs(result);
        }

        public boolean isValid(String origin, String destination) {
            // isTransitValid
            return toTransit.getOrigin().equals(origin) && fromTransit.getDestination().equals(destination)

                    // is Seats Available
                    && (toTransit.getBookingInfo().stream().anyMatch(bookingInfo -> bookingInfo.getSeatsAvailable() > 1) &&
                    fromTransit.getBookingInfo().stream().anyMatch(bookingInfo -> bookingInfo.getSeatsAvailable() > 1)
            )

                    // check for enough time for departure
                    && fromTransit.getDepartureTime().isAfter(toTransit.getArrivalTime().plusHours(CONNECTION_FLIGHT_DURATION));
        }

    }

}
