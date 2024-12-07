package com.webpayglobal.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "card-service")
public interface CardServiceClient {
    @PutMapping("/api/cards/{cardNumber}/activate")
    void activateCard(@PathVariable String cardNumber, @RequestParam Long planId);
}