package com.webpayglobal.kyc.repository;

import com.webpayglobal.kyc.model.KycVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KycVerificationRepository extends JpaRepository<KycVerification, Long> {
    Optional<KycVerification> findByUserId(String userId);
}