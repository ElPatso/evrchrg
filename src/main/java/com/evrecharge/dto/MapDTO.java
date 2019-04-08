package com.evrecharge.dto;

import lombok.Data;

import java.util.List;

@Data
public class MapDTO {
    private AddressDTO currentLocation;
    private List<AddressDTO> closestChargers;
}
