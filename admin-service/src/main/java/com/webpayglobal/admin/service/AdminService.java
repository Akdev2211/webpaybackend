package com.webpayglobal.admin.service;

import com.webpayglobal.admin.client.CardServiceClient;
import com.webpayglobal.admin.client.KycServiceClient;
import com.webpayglobal.admin.model.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CardServiceClient cardServiceClient;
    private final KycServiceClient kycServiceClient;

    public Page<UserSummary> getUserSummaries(Pageable pageable) {
        // Implementation to fetch user summaries from various services
        return null; // Placeholder
    }

    @Transactional
    public void activateUserCard(String cardNumber, Long planId) {
        cardServiceClient.activateCard(cardNumber, planId);
    }

    @Transactional
    public void verifyKycDocument(Long documentId, boolean approved, String rejectionReason) {
        kycServiceClient.verifyDocument(documentId, approved, rejectionReason);
    }

    public void updateUserBalance(String userId, BigDecimal newBalance) {
        // Implementation to update user balance
    }
}