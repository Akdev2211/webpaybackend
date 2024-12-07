package com.webpayglobal.wallet.service;

import com.webpayglobal.wallet.model.Transaction;
import com.webpayglobal.wallet.model.Wallet;

import java.math.BigDecimal;

/**
 * Interface for Wallet Service operations.
 */
public interface WalletService {

    /**
     * Creates a new wallet for a user.
     *
     * @param userId The unique identifier of the user.
     * @return The created wallet.
     */
    Wallet createWallet(String userId);

    /**
     * Deposits a specified amount into the user's wallet.
     *
     * @param userId The unique identifier of the user.
     * @param amount The amount to be deposited.
     * @return The deposit transaction.
     */
    Transaction deposit(String userId, BigDecimal amount);

    /**
     * Withdraws a specified amount from the user's wallet.
     *
     * @param userId The unique identifier of the user.
     * @param amount The amount to be withdrawn.
     * @return The withdrawal transaction.
     */
    Transaction withdraw(String userId, BigDecimal amount);
}