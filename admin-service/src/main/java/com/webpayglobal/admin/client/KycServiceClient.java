package com.webpayglobal.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "kyc-service")
public interface KycServiceClient {
    @PutMapping("/api/kyc/documents/{documentId}/verify")
    void verifyDocument(@PathVariable Long documentId, 
                       @RequestParam boolean approved,
                       @RequestParam String rejectionReason);
}