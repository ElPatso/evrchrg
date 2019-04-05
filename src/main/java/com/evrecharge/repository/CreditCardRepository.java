package com.evrecharge.repository;

import com.evrecharge.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository  extends JpaRepository<CreditCard, Long> {
}
