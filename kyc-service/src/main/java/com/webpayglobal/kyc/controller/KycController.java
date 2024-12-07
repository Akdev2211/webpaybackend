package com.webpayglobal.kyc.controller;

import com.webpayglobal.kyc.model.KycDocument;
import com.webpayglobal.kyc.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycService kycService;

    @PostMapping("/documents")
    public ResponseEntity<KycDocument> submitDocument(
            @RequestParam String userId,
            @RequestParam String documentType,
            @RequestParam String documentUrl) {
        return ResponseEntity.ok(kycService.submitDocument(userId, documentType, documentUrl));
    }

    @PutMapping("/documents/{documentId}/verify")
    public ResponseEntity<KycDocument> verifyDocument(
            @PathVariable Long documentId,
            @RequestParam boolean approved,
            @RequestParam(required = false) String rejectionReason) {
        return ResponseEntity.ok(kycService.verifyDocument(documentId, approved, rejectionReason));
    }
}