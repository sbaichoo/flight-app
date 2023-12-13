package com.maureva.exception;

public class ApplicationException extends RuntimeException{

    public ApplicationException() {
        super("The requested flight is unavailable.");
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
