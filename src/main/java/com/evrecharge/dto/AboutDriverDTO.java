package com.evrecharge.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AboutDriverDTO {
    private Long id;
    private String user;
    private String car;
    private String arriving;
    private String leaving;
    private String pricing;
}
