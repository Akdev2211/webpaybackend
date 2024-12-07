package com.webpayglobal.wallet.service;

import com.webpayglobal.wallet.model.Wallet;
import com.webpayglobal.wallet.model.Transaction;
import com.webpayglobal.wallet.repository.WalletRepository;
import com.webpayglobal.wallet.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementation of the WalletService interface for managing wallet operations.
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Wallet createWallet(String userId) {
        if (walletRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Wallet already exists for user");
        }

        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setBitcoinAddress(generateBitcoinAddress());
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Transaction deposit(String userId, BigDecimal amount) {
        validateAmount(amount);

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        Transaction transaction = createTransaction(wallet, Transaction.TransactionType.DEPOSIT, amount, Transaction.TransactionStatus.COMPLETED);

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction withdraw(String userId, BigDecimal amount) {
        validateAmount(amount);

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        Transaction transaction = createTransaction(wallet, Transaction.TransactionType.WITHDRAWAL, amount, Transaction.TransactionStatus.PENDING);

        return transactionRepository.save(transaction);
    }

    private String generateBitcoinAddress() {
        return "bc1" + UUID.randomUUID().toString().replace("-", "");
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private Transaction createTransaction(Wallet wallet, Transaction.TransactionType type, BigDecimal amount, Transaction.TransactionStatus status) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setWallet(wallet);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus(status);
        return transaction;
    }
}