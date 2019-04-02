package com.evrecharge.dto;

import com.evrecharge.entity.Request;
import com.evrecharge.util.DateTimeUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RequestDTO {
    private String user;
    private String from;
    private String to;
    private String car;
    private String status;

    public static RequestDTO toDTO(Request request){
        return new RequestDTO()
                .setUser(request.getUser().getFirstname() + " " + request.getUser().getLastname())
                .setStatus(request.getStatus().getName())
                .setFrom(DateTimeUtil.TIME.format(request.getChargeFrom()))
                .setTo(DateTimeUtil.TIME.format(request.getChargeTo()));
    }
}
