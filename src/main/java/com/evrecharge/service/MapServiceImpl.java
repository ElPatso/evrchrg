package com.evrecharge.service;

import com.evrecharge.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Override
    public List<AddressDTO> getAddresses() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLat(BigDecimal.ONE);
        addressDTO.setLng(BigDecimal.ONE);
        addressDTO.setTitle("1");

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setLat(BigDecimal.ONE);
        addressDTO2.setLng(BigDecimal.ONE);
        addressDTO2.setTitle("1");
        List<AddressDTO> list = Arrays.asList(addressDTO, addressDTO2);
        return list;
    }
}
