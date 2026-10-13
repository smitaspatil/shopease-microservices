package com.shopease.notification_service.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-service"
    )
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println(
                "Order notification received: orderId="
                        + event.orderId()
                        + ", userId="
                        + event.userId()
                        + ", productId="
                        + event.productId()
                        + ", quantity="
                        + event.quantity()
                        + ", total="
                        + event.totalAmount()
                        + ", status="
                        + event.status()
        );
    }
}