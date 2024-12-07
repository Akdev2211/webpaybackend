package com.webpayglobal.admin.controller;

import com.webpayglobal.admin.model.UserSummary;
import com.webpayglobal.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserSummary>> getUserSummaries(Pageable pageable) {
        return ResponseEntity.ok(adminService.getUserSummaries(pageable));
    }

    @PutMapping("/cards/{cardNumber}/activate")
    public ResponseEntity<Void> activateCard(
            @PathVariable String cardNumber,
            @RequestParam Long planId) {
        adminService.activateUserCard(cardNumber, planId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/kyc/documents/{documentId}")
    public ResponseEntity<Void> verifyKycDocument(
            @PathVariable Long documentId,
            @RequestParam boolean approved,
            @RequestParam(required = false) String rejectionReason) {
        adminService.verifyKycDocument(documentId, approved, rejectionReason);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}/balance")
    public ResponseEntity<Void> updateUserBalance(
            @PathVariable String userId,
            @RequestParam BigDecimal newBalance) {
        adminService.updateUserBalance(userId, newBalance);
        return ResponseEntity.ok().build();
    }
}