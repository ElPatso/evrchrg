package com.evrecharge.service;

import com.evrecharge.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
   void createFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getFeedbackListByChargePoint(Long id);
}
