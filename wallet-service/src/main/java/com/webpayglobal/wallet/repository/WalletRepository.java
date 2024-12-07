package com.webpayglobal.wallet.repository;

import com.webpayglobal.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserId(String userId);
    boolean existsByUserId(String userId);
}