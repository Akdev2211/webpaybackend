package com.webpayglobal.admin.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserSummary {
    private String userId;
    private String email;
    private BigDecimal balance;
    private String cardStatus;
    private String kycStatus;
    private LocalDateTime registrationDate;
}