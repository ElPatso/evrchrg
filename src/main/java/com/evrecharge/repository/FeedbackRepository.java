package com.evrecharge.repository;

import com.evrecharge.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query(value = "SELECT f FROM Feedback f WHERE f.chargePoint.id = :id")
    List<Feedback> getFeedbackListByChargePointId(@Param("id") Long id);
}
