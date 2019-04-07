package com.evrecharge.repository;

import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByUser(User user);

    @Query(value = "SELECT r FROM Request r JOIN r.point p  WHERE p.owner =:user")
    List<Request> findAllReceivedByUser(@Param("user")User user);
}
