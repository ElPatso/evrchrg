package com.evrecharge.service;

import com.evrecharge.dto.AboutDriverDTO;
import com.evrecharge.dto.RequestDTO;
import com.evrecharge.dto.RequestInfoDTO;
import com.evrecharge.entity.Request;

import java.util.List;

public interface RequestService {
    List<RequestDTO> getSentRequestsByCurrentUser();

    List<RequestDTO> getReceivedRequestsByCurrentUser();

    RequestInfoDTO getInfoById(Long id);

    AboutDriverDTO getRequestDriverInfo(Long id);

    void changeStatus(String status, Long id);
}
