package com.webpayglobal.wallet.repository;

import com.webpayglobal.wallet.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    /**
     * Finds a wallet entity by the associated user identifier.
     *
     * @param userId the unique user identifier
     * @return an optional containing the found wallet, if present
     */
    Optional<Wallet> findByUserId(String userId);

    /**
     * Checks the existence of a wallet associated with a specific user identifier.
     *
     * @param userId the unique user identifier
     * @return true if a wallet exists for the given user ID, false otherwise
     */
    boolean existsByUserId(String userId);
}