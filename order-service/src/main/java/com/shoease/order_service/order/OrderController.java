package com.shoease.order_service.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(
            OrderApplicationService orderApplicationService
    ) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return orderApplicationService.create(request);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable UUID id) {
        return orderApplicationService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> findByUserId(@PathVariable UUID userId) {
        return orderApplicationService.findByUserId(userId);
    }
}