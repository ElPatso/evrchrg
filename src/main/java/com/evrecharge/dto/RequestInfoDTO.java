package com.evrecharge.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RequestInfoDTO {
    private Long id;
    private String status;
    private String owner;
    private String address;
    private String type;
    private String openingHours;
    private String description;
    private String instruction;
    private String arriving;
    private String leaving;
    private String pricing;
}
