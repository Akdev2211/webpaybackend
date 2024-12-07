package com.webpayglobal.card.repository;

import com.webpayglobal.card.model.CardPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPlanRepository extends JpaRepository<CardPlan, Long> {
}