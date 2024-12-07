package com.webpayglobal.notification.repository;

import com.webpayglobal.notification.model.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
    List<EmailNotification> findByRecipientOrderByCreatedAtDesc(String recipient);
    List<EmailNotification> findByStatus(EmailNotification.NotificationStatus status);
}