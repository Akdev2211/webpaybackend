package com.webpayglobal.wallet.repository;

import com.webpayglobal.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletIdOrderByTimestampDesc(Long walletId);
    Optional<Transaction> findByTransactionId(String transactionId);
}