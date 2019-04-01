package com.evrecharge.repository;

import com.evrecharge.entity.ChargePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {
}
