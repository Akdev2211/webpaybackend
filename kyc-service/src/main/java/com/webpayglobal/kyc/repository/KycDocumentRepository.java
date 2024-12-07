package com.webpayglobal.kyc.repository;

import com.webpayglobal.kyc.model.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KycDocumentRepository extends JpaRepository<KycDocument, Long> {
    List<KycDocument> findByUserId(String userId);
    List<KycDocument> findByStatus(KycDocument.VerificationStatus status);
}