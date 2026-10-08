package com.shoease.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse findById(@PathVariable("id") UUID id);

    @PatchMapping("/products/{id}/stock")
    ProductResponse updateStock(
            @PathVariable("id") UUID id,
            @RequestParam("quantity") int quantity
    );
}
