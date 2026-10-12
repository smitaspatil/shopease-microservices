package com.shoease.order_service.client;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserClientFallbackFactory
        implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {

            @Override
            public UserResponse findById(UUID id) {
                if (cause instanceof FeignException.NotFound) {
                    throw new UserNotFoundException(id);
                }

                throw new ServiceUnavailableException("User Service is unavailable", cause);
            }
        };
    }
}