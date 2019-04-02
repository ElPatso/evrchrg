package com.evrecharge.repository;

import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByUser(User user);
}
