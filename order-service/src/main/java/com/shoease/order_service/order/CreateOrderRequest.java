package com.shoease.order_service.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderRequest(
        @NotNull UUID userId,
        @NotNull UUID productId,
        @NotNull @Min(1) Integer quantity
) {
}
