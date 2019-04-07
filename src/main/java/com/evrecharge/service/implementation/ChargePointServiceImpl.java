package com.evrecharge.service.implementation;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.dto.RentDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.entity.Request;
import com.evrecharge.entity.enums.ChargeTypeEnum;
import com.evrecharge.entity.enums.Currency;
import com.evrecharge.entity.enums.RequestStatusEnum;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.service.ChargePointService;
import com.evrecharge.service.UserService;
import com.evrecharge.util.DateTimeUtil;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ChargePointServiceImpl implements ChargePointService {
    private ChargePointRepository chargePointRepository;
    private StripeServiceImpl stripeServiceImpl;
    private UserService userService;

    @Autowired
    public ChargePointServiceImpl(ChargePointRepository chargePointRepository,
                                  StripeServiceImpl requestRepository,
                                  UserService userService) {
        this.chargePointRepository = chargePointRepository;
        this.stripeServiceImpl = requestRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public ChargePointInfoDTO getRentInfo(Long id) {
        final ChargePoint chargePoint = chargePointRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        ChargePointInfoDTO chargePointInfoDTO = new ChargePointInfoDTO();
        chargePointInfoDTO.setOwner(chargePoint.getOwner().getFirstname());
        chargePointInfoDTO.setPostCode(chargePoint.getPostCode());
        chargePointInfoDTO.setDescription(chargePoint.getDescription());
        chargePointInfoDTO.setType(chargePoint.getChargeType().getName());
        chargePointInfoDTO.setId(id);
        chargePointInfoDTO.setAvailableTime(getAvailableTime(chargePoint));
        return chargePointInfoDTO;
    }

    @Override
    @Transactional
    public void bookChargePoint(RentDTO rentDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        final ChargePoint chargePoint = chargePointRepository.findById(rentDTO.getId()).orElseThrow(IllegalAccessError::new);
        final Request request = new Request();
        LocalDateTime from = LocalDateTime.parse(rentDTO.getTime(), DateTimeUtil.DATE_TIME_FORMATTER);
        LocalDateTime to = from.plusHours(rentDTO.getDuration());
        PriceDTO priceDTO = calculatePrice(chargePoint.getChargeType().getName(), rentDTO.getDuration());
        request.setChargeFrom(from);
        request.setChargeTo(to);
        request.setPoint(chargePoint);
        request.setCharged(priceDTO.getPrice());
        request.setStatus(RequestStatusEnum.PENDING);
        request.setUser(userService.getCurrentUser());
        chargePoint.getRequest().add(request);
        chargePointRepository.save(chargePoint);
        stripeServiceImpl.charge(chargePoint.getOwner(), priceDTO, rentDTO.getToken());
    }

    @Override
    @Transactional
    public PriceDTO calculatePrice(String type, Integer duration) {
        ChargeTypeEnum chargeTypeEnum = ChargeTypeEnum.getEnum(type);
        return new PriceDTO(chargeTypeEnum.getPrice(new BigDecimal(duration)), Currency.USD.getSymbol(), Currency.USD.getName());
    }

    private String getAvailableTime(ChargePoint chargePoint) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chargePoint.getAvailableFrom().getHour()).append(":").append(chargePoint.getAvailableFrom().getMinute());
        stringBuilder.append(" - ");
        stringBuilder.append(chargePoint.getAvailableTo().getHour()).append(":").append(chargePoint.getAvailableTo().getMinute());
        return stringBuilder.toString();
    }
}
