package com.maureva.exception;

public class UnavailableFlightException extends RuntimeException {

    public UnavailableFlightException() {
        super("The requested flight is unavailable.");
    }

    public UnavailableFlightException(String message) {
        super(message);
    }

    public UnavailableFlightException(String message, Throwable cause) {
        super(message, cause);
    }
}
