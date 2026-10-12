package com.shoease.order_service.client;

import org.springframework.cloud.openfeign.FallbackFactory;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(java.util.UUID userId) {
        super("User not found: " + userId);
    }
}
