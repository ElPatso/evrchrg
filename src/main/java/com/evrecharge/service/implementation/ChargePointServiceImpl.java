package com.evrecharge.service.implementation;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.entity.Request;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.ChargePointService;
import com.evrecharge.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ChargePointServiceImpl implements ChargePointService {

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

        return chargePointInfoDTO;
    }

    @Override
    @Transactional
    public void bookChargePoint(ChargePointInfoDTO requestDTO) {
        final ChargePoint chargePoint = chargePointRepository.findById(requestDTO.getId()).orElseThrow(IllegalAccessError::new);
        final Request request = new Request();
        //LocalDateTime from = LocalDateTime.now().withHour().withMinute();
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(requestDTO.getDuration());
        request.setChargeFrom(from);
        request.setChargeTo(to);
        request.setPoint(chargePoint);
        //request.setUser(currentUser);
        chargePoint.setRequest(request);
        chargePointRepository.save(chargePoint);
    }
}
