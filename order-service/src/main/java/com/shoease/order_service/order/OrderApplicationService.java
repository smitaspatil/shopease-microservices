package com.shoease.order_service.order;

import com.shoease.order_service.client.ProductClient;
import com.shoease.order_service.client.ProductResponse;
import com.shoease.order_service.client.UserClient;
import feign.FeignException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderApplicationService(
            OrderRepository orderRepository,
            UserClient userClient,
            ProductClient productClient
    ) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest request) {
        try {
            userClient.findById(request.userId());

            ProductResponse product =
                    productClient.findById(request.productId());

            if (!Boolean.TRUE.equals(product.active())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Product is inactive"
                );
            }

            if (product.stockQuantity() < request.quantity()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Insufficient stock"
                );
            }

            BigDecimal totalAmount = product.price()
                    .multiply(BigDecimal.valueOf(request.quantity()));

            productClient.updateStock(
                    request.productId(),
                    -request.quantity()
            );

            Order order = new Order(
                    request.userId(),
                    request.productId(),
                    request.quantity(),
                    totalAmount,
                    OrderStatus.CONFIRMED
            );

            return toResponse(orderRepository.save(order));

        } catch (FeignException.NotFound exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User or product was not found"
            );
        }
    }

    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found"
                ));

        return toResponse(order);
    }

    public List<OrderResponse> findByUserId(UUID userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
