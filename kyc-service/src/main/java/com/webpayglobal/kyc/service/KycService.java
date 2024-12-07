package com.webpayglobal.kyc.service;

import com.webpayglobal.kyc.model.KycDocument;
import com.webpayglobal.kyc.model.KycVerification;
import com.webpayglobal.kyc.repository.KycDocumentRepository;
import com.webpayglobal.kyc.repository.KycVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KycService {
    private final KycDocumentRepository documentRepository;
    private final KycVerificationRepository verificationRepository;

    @Transactional
    public KycDocument submitDocument(String userId, String documentType, String documentUrl) {
        KycDocument document = new KycDocument();
        document.setUserId(userId);
        document.setDocumentType(documentType);
        document.setDocumentUrl(documentUrl);
        document.setStatus(KycDocument.VerificationStatus.PENDING);
        
        return documentRepository.save(document);
    }

    @Transactional
    public KycDocument verifyDocument(Long documentId, boolean approved, String rejectionReason) {
        KycDocument document = documentRepository.findById(documentId)
            .orElseThrow(() -> new RuntimeException("Document not found"));

        document.setStatus(approved ? 
            KycDocument.VerificationStatus.APPROVED : 
            KycDocument.VerificationStatus.REJECTED);
        
        if (!approved) {
            document.setRejectionReason(rejectionReason);
        }

        updateVerificationStatus(document.getUserId());
        return documentRepository.save(document);
    }

    private void updateVerificationStatus(String userId) {
        List<KycDocument> documents = documentRepository.findByUserId(userId);
        KycVerification verification = verificationRepository.findByUserId(userId)
            .orElse(new KycVerification());

        verification.setUserId(userId);
        verification.setIdVerified(isDocumentTypeVerified(documents, "ID"));
        verification.setAddressVerified(isDocumentTypeVerified(documents, "ADDRESS"));
        verification.setPhotoVerified(isDocumentTypeVerified(documents, "PHOTO"));

        updateOverallStatus(verification);
        verificationRepository.save(verification);
    }

    private boolean isDocumentTypeVerified(List<KycDocument> documents, String type) {
        return documents.stream()
            .filter(doc -> doc.getDocumentType().equals(type))
            .anyMatch(doc -> doc.getStatus() == KycDocument.VerificationStatus.APPROVED);
    }

    private void updateOverallStatus(KycVerification verification) {
        if (verification.isIdVerified() && verification.isAddressVerified() && verification.isPhotoVerified()) {
            verification.setOverallStatus(KycVerification.VerificationStatus.FULLY_VERIFIED);
        } else if (verification.isIdVerified() || verification.isAddressVerified() || verification.isPhotoVerified()) {
            verification.setOverallStatus(KycVerification.VerificationStatus.PARTIALLY_VERIFIED);
        } else {
            verification.setOverallStatus(KycVerification.VerificationStatus.PENDING);
        }
    }
}