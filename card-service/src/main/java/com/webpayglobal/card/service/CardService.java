package com.webpayglobal.card.service;

import com.webpayglobal.card.model.Card;
import com.webpayglobal.card.model.CardPlan;
import com.webpayglobal.card.repository.CardRepository;
import com.webpayglobal.card.repository.CardPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardPlanRepository cardPlanRepository;

    @Transactional
    public Card createCard(String userId) {
        CardPlan basicPlan = cardPlanRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Basic plan not found"));

        Card card = new Card();
        card.setUserId(userId);
        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCVV());
        card.setExpiryDate(LocalDateTime.now().plusYears(3));
        card.setStatus(Card.CardStatus.INACTIVE);
        card.setPlan(basicPlan);

        return cardRepository.save(card);
    }

    @Transactional
    public Card activateCard(String cardNumber, Long planId) {
        Card card = cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new RuntimeException("Card not found"));
            
        CardPlan plan = cardPlanRepository.findById(planId)
            .orElseThrow(() -> new RuntimeException("Plan not found"));

        card.setStatus(Card.CardStatus.ACTIVE);
        card.setPlan(plan);

        return cardRepository.save(card);
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("4");
        for (int i = 0; i < 15; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generateCVV() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }
}