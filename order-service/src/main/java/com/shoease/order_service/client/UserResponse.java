package com.shoease.order_service.client;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String role
) {
}
