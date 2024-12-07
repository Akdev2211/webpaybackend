package com.webpayglobal.wallet.controller;

import com.webpayglobal.wallet.model.Transaction;
import com.webpayglobal.wallet.model.Wallet;
import com.webpayglobal.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST Controller for wallet-related operations.
 */
@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;  // This will resolve to WalletServiceImpl

    /**
     * Endpoint to create a new wallet for a user.
     *
     * @param userId Identifier of the user for which the wallet is to be created.
     * @return The created wallet wrapped in ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestParam String userId) {
        Wallet createdWallet = walletService.createWallet(userId);
        return ResponseEntity.ok(createdWallet);
    }

    /**
     * Endpoint to deposit into a user's wallet.
     *
     * @param userId Identifier of the user owning the wallet.
     * @param amount The amount to be deposited.
     * @return The transaction details wrapped in ResponseEntity.
     */
    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Transaction> deposit(
            @PathVariable String userId,
            @RequestParam BigDecimal amount) {
        Transaction transaction = walletService.deposit(userId, amount);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Endpoint to withdraw from a user's wallet.
     *
     * @param userId Identifier of the user owning the wallet.
     * @param amount The amount to be withdrawn.
     * @return The transaction details wrapped in ResponseEntity.
     */
    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<Transaction> withdraw(
            @PathVariable String userId,
            @RequestParam BigDecimal amount) {
        Transaction transaction = walletService.withdraw(userId, amount);
        return ResponseEntity.ok(transaction);
    }
}