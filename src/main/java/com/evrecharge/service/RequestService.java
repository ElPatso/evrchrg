package com.evrecharge.service;

import com.evrecharge.dto.RequestDTO;
import com.evrecharge.entity.Request;

import java.util.List;

public interface RequestService {
    List<RequestDTO> getSentRequestsByCurrentUser();

    List<RequestDTO> getReceivedRequestsByCurrentUser();
}
