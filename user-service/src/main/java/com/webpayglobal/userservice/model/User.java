package com.webpayglobal.userservice.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String phoneNumber;
    
    private String country;
    
    private String state;
    
    private String city;
    
    private String zipCode;
    
    private boolean emailVerified;
    
    private String googleAuthSecret;
    
    private boolean googleAuthEnabled;
    
    private LocalDateTime lastLoginTime;
    
    private String lastLoginIp;
    
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}