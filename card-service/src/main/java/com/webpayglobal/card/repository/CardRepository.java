package com.webpayglobal.card.repository;

import com.webpayglobal.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findByUserId(String userId);
    boolean existsByCardNumber(String cardNumber);
}