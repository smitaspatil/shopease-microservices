package com.shoease.order_service.client;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(java.util.UUID productId) {
        super("Product not found: " + productId);
    }
}
