package com.webpayglobal.wallet.repository;

import com.webpayglobal.wallet.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Finds a list of transactions associated with a wallet, ordered by timestamp descending.
     *
     * @param walletId the identifier of the wallet
     * @return list of transactions sorted by timestamp
     */
    List<Transaction> findByWalletIdOrderByTimestampDesc(Long walletId);

    /**
     * Finds a transaction by its unique transaction identifier.
     *
     * @param transactionId the unique transaction identifier
     * @return an optional containing the found transaction, if present
     */
    Optional<Transaction> findByTransactionId(String transactionId);
}