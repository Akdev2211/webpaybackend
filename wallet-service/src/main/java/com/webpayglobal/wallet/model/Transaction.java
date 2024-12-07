package com.webpayglobal.wallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a financial transaction linked to a wallet.
 */
@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    /**
     * Unique identifier for the transaction entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique identifier for the transaction.
     */
    @NotBlank(message = "Transaction ID cannot be empty")
    @Column(nullable = false)
    private String transactionId;

    /** Associated wallet for the transaction. */
    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    /** Type of the transaction (e.g., deposit, withdrawal). */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    /**
     * Amount involved in the transaction.
     */
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @Digits(integer = 19, fraction = 2, message = "Invalid amount format")
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Additional information about the transaction.
     */
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /** Timestamp when the transaction occurred. */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /** Current status of the transaction. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    /** Enumeration representing possible transaction types. */
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    /** Enumeration representing possible transaction statuses. */
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED
    }
}