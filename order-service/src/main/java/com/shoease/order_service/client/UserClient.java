package com.shoease.order_service.client;

import com.shoease.order_service.config.FeignLoggingConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
@FeignClient(
        name = "user-service",
        configuration = FeignLoggingConfig.class
)
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse findById(@PathVariable("id") UUID id);
}