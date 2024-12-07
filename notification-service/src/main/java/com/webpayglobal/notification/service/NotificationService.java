package com.webpayglobal.notification.service;

import com.webpayglobal.notification.model.EmailNotification;
import com.webpayglobal.notification.repository.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailNotificationRepository notificationRepository;
    private final EmailService emailService;

    @Transactional
    public EmailNotification sendNotification(String recipient, String subject, 
            String content, EmailNotification.NotificationType type) {
        EmailNotification notification = new EmailNotification();
        notification.setRecipient(recipient);
        notification.setSubject(subject);
        notification.setContent(content);
        notification.setType(type);
        notification.setStatus(EmailNotification.NotificationStatus.PENDING);

        notification = notificationRepository.save(notification);
        emailService.sendEmail(notification);
        
        return notificationRepository.save(notification);
    }

    public void sendWelcomeEmail(String email) {
        sendNotification(
            email,
            "Welcome to WebPay Global",
            "Welcome to WebPay Global! We're excited to have you on board.",
            EmailNotification.NotificationType.WELCOME
        );
    }

    public void sendLoginAlert(String email, String ipAddress) {
        sendNotification(
            email,
            "New Login Detected",
            "A new login was detected from IP: " + ipAddress,
            EmailNotification.NotificationType.LOGIN_ALERT
        );
    }

    public void sendTransactionAlert(String email, String transactionDetails) {
        sendNotification(
            email,
            "Transaction Alert",
            "Transaction details: " + transactionDetails,
            EmailNotification.NotificationType.TRANSACTION_ALERT
        );
    }
}