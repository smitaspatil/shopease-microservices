package com.shoease.order_service.client;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}