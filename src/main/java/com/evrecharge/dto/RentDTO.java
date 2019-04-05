package com.evrecharge.dto;

import lombok.Data;

@Data
public class RentDTO {
    private Long id;
    private String token;
    private Integer duration;
    private String time;
}
