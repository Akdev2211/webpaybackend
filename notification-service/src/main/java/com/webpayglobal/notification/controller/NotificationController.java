package com.webpayglobal.notification.controller;

import com.webpayglobal.notification.model.EmailNotification;
import com.webpayglobal.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/welcome")
    public ResponseEntity<Void> sendWelcomeEmail(@RequestParam String email) {
        notificationService.sendWelcomeEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login-alert")
    public ResponseEntity<Void> sendLoginAlert(
            @RequestParam String email,
            @RequestParam String ipAddress) {
        notificationService.sendLoginAlert(email, ipAddress);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transaction-alert")
    public ResponseEntity<Void> sendTransactionAlert(
            @RequestParam String email,
            @RequestParam String transactionDetails) {
        notificationService.sendTransactionAlert(email, transactionDetails);
        return ResponseEntity.ok().build();
    }
}