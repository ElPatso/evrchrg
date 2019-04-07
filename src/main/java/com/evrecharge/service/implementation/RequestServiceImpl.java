package com.evrecharge.service.implementation;

import com.evrecharge.dto.AboutDriverDTO;
import com.evrecharge.dto.RequestDTO;
import com.evrecharge.dto.RequestInfoDTO;
import com.evrecharge.entity.Car;
import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import com.evrecharge.entity.enums.Currency;
import com.evrecharge.entity.enums.RequestStatusEnum;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.RequestService;
import com.evrecharge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User currentUser = userService.getCurrentUser();
        return requestRepository.findAllByUser(currentUser).stream().map(RequestDTO::toSentDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<RequestDTO> getReceivedRequestsByCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return requestRepository.findAllReceivedByUser(currentUser).stream().map(RequestDTO::toReceivedDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RequestInfoDTO getInfoById(Long id) {
        Request request = requestRepository.getOne(id);
        RequestInfoDTO requestInfoDTO = new RequestInfoDTO()
                .setId(request.getId())
                .setAddress(request.getPoint().getAddress())
                .setDescription(request.getPoint().getDescription())
                .setInstruction(request.getPoint().getAccessInstructions())
                .setOpeningHours("Check Availability Calendar")
                .setType(request.getPoint().getChargeType().getName())
                .setOwner(request.getPoint().getOwner().getFirstname() + " " + request.getPoint().getOwner().getLastname())
                .setStatus(request.getStatus().getName())
                .setPricing(Currency.USD.getSymbol() + "" + request.getCharged())
                .setArriving(request.getChargeFrom().getHour() + ":" + request.getChargeFrom().getMinute())
                .setLeaving(request.getChargeTo().getHour() + ":" + request.getChargeTo().getMinute());

        return requestInfoDTO;
    }

    @Override
    @Transactional
    public AboutDriverDTO getRequestDriverInfo(Long id) {
        final Request request = requestRepository.getOne(id);
        final User user = request.getUser();
        final Car car = user.getCar();
        return new AboutDriverDTO()
                .setId(request.getId())
                .setUser(user.getFirstname() + " " + user.getFirstname())
                .setCar(car.getCar() + "-" + car.getYear() + "-" + car.getModel())
                .setArriving(request.getChargeFrom().getHour() + ":" + request.getChargeFrom().getMinute())
                .setLeaving(request.getChargeTo().getHour() + ":" + request.getChargeTo().getMinute())
                .setPricing(Currency.USD.getSymbol() + " " + request.getCharged());
    }

    @Override
    @Transactional
    public void changeStatus(String status, Long id) {
        final Request request = requestRepository.getOne(id);
        RequestStatusEnum requestStatusEnum = RequestStatusEnum.getEnum(status);
        request.setStatus(requestStatusEnum);
        requestRepository.save(request);
    }
}
