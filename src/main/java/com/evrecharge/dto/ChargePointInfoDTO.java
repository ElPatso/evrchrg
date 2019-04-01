package com.evrecharge.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChargePointInfoDTO {
    private Long id;
    private String owner;
    private String postCode;
    private String type;
    private String availableTime;
    private String description;
    private List<String> images;

    private String time;
    private String duration;

}
