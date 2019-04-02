package com.evrecharge.dto;

import com.evrecharge.entity.Feedback;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FeedbackDTO {
    private short rate;
    private String description;
    private Long chargePointId;

    public static FeedbackDTO toDTO(Feedback feedback){
        return new FeedbackDTO()
                .setRate(feedback.getRate())
                .setDescription(feedback.getDescription());
    }
}
