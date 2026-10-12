package com.shoease.order_service.client;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductClientFallbackFactory
        implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {

            @Override
            public ProductResponse findById(UUID id) {
                if (cause instanceof FeignException.NotFound) {
                    throw new ProductNotFoundException(id);
                }

                throw new ServiceUnavailableException(
                        "Product Service is unavailable",
                        cause
                );
            }

            @Override
            public ProductResponse updateStock(
                    UUID id,
                    int quantity
            ) {
                throw new ServiceUnavailableException(
                        "Product Service is unavailable while updating stock",
                        cause
                );
            }
        };
    }
}