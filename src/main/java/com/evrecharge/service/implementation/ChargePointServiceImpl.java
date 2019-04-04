package com.evrecharge.service.implementation;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.entity.Request;
import com.evrecharge.entity.enums.ChargeTypeEnum;
import com.evrecharge.entity.enums.RequestStatusEnum;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.ChargePointService;
import com.evrecharge.util.DateTimeUtil;
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
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private ChargePointRepository chargePointRepository;
    private RequestRepository requestRepository;

    @Autowired
    public ChargePointServiceImpl(ChargePointRepository chargePointRepository, RequestRepository requestRepository) {
        this.chargePointRepository = chargePointRepository;
        this.requestRepository = requestRepository;
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
    public void bookChargePoint(ChargePointInfoDTO requestDTO) {
        final ChargePoint chargePoint = chargePointRepository.findById(requestDTO.getId()).orElseThrow(IllegalAccessError::new);
        final Request request = new Request();
        LocalTime time = LocalTime.parse(requestDTO.getTime());
        LocalDateTime from = LocalDateTime.now().withHour(time.getHour()).withMinute(time.getMinute());
        LocalDateTime to = from.plusHours(requestDTO.getDuration());
        request.setChargeFrom(from);
        request.setChargeTo(to);
        request.setPoint(chargePoint);
        request.setCharged(calculatePrice(chargePoint.getChargeType().getName(), requestDTO.getDuration()).getPrice());
        request.setStatus(RequestStatusEnum.PENDING);
        //request.setUser(currentUser);
        chargePoint.getRequest().add(request);
        chargePointRepository.save(chargePoint);
    }

    @Override
    @Transactional
    public PriceDTO calculatePrice(String type, Integer duration) {
        ChargeTypeEnum chargeTypeEnum = ChargeTypeEnum.getEnum(type);
        return new PriceDTO(chargeTypeEnum.getPrice(new BigDecimal(duration)), "$");
    }

    private String getAvailableTime(ChargePoint chargePoint) {
        Set<String> dates = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        dates.add(chargePoint.getAvailableFrom().format(DATE_TIME_FORMATTER));
        for (LocalDateTime from = chargePoint.getAvailableFrom();
             from.isBefore(chargePoint.getAvailableTo()); from = from.plusMinutes(5)) {
            for (Request request : chargePoint.getRequest()) {
                if (request.getChargeFrom()
                        .withSecond(0).equals(from.withSecond(0))) {
                    from = request.getChargeTo().minusMinutes(5);
                    dates.add(request.getChargeFrom().format(DATE_TIME_FORMATTER));
                    dates.add(request.getChargeTo().format(DATE_TIME_FORMATTER));
                    break;
                }
            }
        }
        dates.add(chargePoint.getAvailableTo().format(DATE_TIME_FORMATTER));

        return sb.toString();
    }
}
