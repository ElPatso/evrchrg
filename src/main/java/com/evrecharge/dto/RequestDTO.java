package com.evrecharge.dto;

import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import com.evrecharge.util.DateTimeUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RequestDTO {
    private Long id;
    private String user;
    private String from;
    private String to;
    private String car;
    private String status;
    private String url;

    public static RequestDTO toSentDTO(Request request) {
        User targetUser = request.getPoint().getOwner();
        return new RequestDTO()
                .setId(request.getId())
                .setUser(targetUser.getFirstname() + " " + targetUser.getLastname())
                .setStatus(request.getStatus().getName())
                .setUrl("info")
                .setFrom(request.getChargeFrom().getHour() + ":" + request.getChargeFrom().getMinute())
                .setTo(request.getChargeTo().getHour() + ":" + request.getChargeTo().getMinute());
    }

    public static RequestDTO toReceivedDTO(Request request) {
        User user = request.getUser();
        return new RequestDTO()
                .setId(request.getId())
                .setUser(user.getFirstname() + " " + user.getLastname())
                .setStatus(request.getStatus().getName())
                .setUrl("request")
                .setFrom(request.getChargeFrom().getHour() + ":" + request.getChargeFrom().getMinute())
                .setTo(request.getChargeTo().getHour() + ":" + request.getChargeTo().getMinute());
    }
}
