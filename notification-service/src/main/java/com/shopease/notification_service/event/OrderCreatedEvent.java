package com.shopease.notification_service.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        UUID userId,
        UUID productId,
        Integer quantity,
        BigDecimal totalAmount,
        String status,
        Instant createdAt
) {
}
