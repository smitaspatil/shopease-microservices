package com.shoease.order_service.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID userId,
        UUID productId,
        Integer quantity,
        BigDecimal totalAmount,
        OrderStatus status,
        Instant createdAt
) {
}