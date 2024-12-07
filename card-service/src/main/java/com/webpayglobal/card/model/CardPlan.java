package com.webpayglobal.card.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "card_plans")
public class CardPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private BigDecimal cost;
    
    @Column(nullable = false)
    private BigDecimal withdrawalLimit;
    
    private String description;
    
    @Column(nullable = false)
    private boolean unlimitedWithdrawals;
}