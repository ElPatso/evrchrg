package com.evrecharge.service.implementation;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestServiceImpl implements RequestService {

    private ChargePointRepository chargePointRepository;
    private RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(ChargePointRepository chargePointRepository, RequestRepository requestRepository) {
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
}
