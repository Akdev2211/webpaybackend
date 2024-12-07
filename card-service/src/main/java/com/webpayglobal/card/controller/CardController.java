package com.webpayglobal.card.controller;

import com.webpayglobal.card.model.Card;
import com.webpayglobal.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestParam String userId) {
        return ResponseEntity.ok(cardService.createCard(userId));
    }

    @PutMapping("/{cardNumber}/activate")
    public ResponseEntity<Card> activateCard(
            @PathVariable String cardNumber,
            @RequestParam Long planId) {
        return ResponseEntity.ok(cardService.activateCard(cardNumber, planId));
    }
}