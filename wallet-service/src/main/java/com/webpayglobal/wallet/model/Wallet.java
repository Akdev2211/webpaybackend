package com.webpayglobal.wallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a wallet entity in the system with financial balance and identifiers.
 */
@Data
@Entity
@Table(name = "wallets")
public class Wallet {

    /**
     * Unique identifier for the wallet entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique user identifier associated with the wallet.
     */
    @NotBlank(message = "User ID cannot be empty")
    @Column(nullable = false, unique = true)
    private String userId;

    /**
     * Current balance maintained in the wallet.
     */
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be positive or zero")
    @Digits(integer = 19, fraction = 2, message = "Invalid balance format")
    @Column(nullable = false)
    private BigDecimal balance;

    /**
     * Bitcoin address linked with the wallet for transactions.
     */
    @NotBlank(message = "Bitcoin address cannot be empty")
    @Column(nullable = false)
    private String bitcoinAddress;

    /**
     * Timestamp when the wallet was created.
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /** Timestamp of the last update to the wallet. */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** Automatically sets creation and update timestamps before persisting a new entity. */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /** Updates the timestamp of the last change before updating the entity. */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}