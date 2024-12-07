package com.webpayglobal.wallet.controller;

import com.webpayglobal.wallet.model.Wallet;
import com.webpayglobal.wallet.model.Transaction;
import com.webpayglobal.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestParam String userId) {
        return ResponseEntity.ok(walletService.createWallet(userId));
    }

    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Transaction> deposit(
            @PathVariable String userId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(walletService.deposit(userId, amount));
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<Transaction> withdraw(
            @PathVariable String userId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(walletService.withdraw(userId, amount));
    }
}