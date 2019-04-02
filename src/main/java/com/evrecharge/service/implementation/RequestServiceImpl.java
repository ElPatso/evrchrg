package com.evrecharge.service.implementation;

import com.evrecharge.dto.RequestDTO;
import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.RequestService;
import com.evrecharge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserService userService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
    }

    @Override
    public List<RequestDTO> getSentRequestsByCurrentUser() {
        User currentUser = null;
        return requestRepository.findAllByUser(currentUser).stream().map(RequestDTO::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<RequestDTO> getReceivedRequestsByCurrentUser() {
        User currentUser = null;
        return null;
    }
}
