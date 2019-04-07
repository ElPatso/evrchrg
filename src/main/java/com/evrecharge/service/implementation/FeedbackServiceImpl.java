package com.evrecharge.service.implementation;

import com.evrecharge.dto.FeedbackDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.entity.Feedback;
import com.evrecharge.entity.User;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.repository.FeedbackRepository;
import com.evrecharge.service.FeedbackService;
import com.evrecharge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ChargePointRepository chargePointRepository;
    private final UserService userService;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ChargePointRepository chargePointRepository,
                               UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.chargePointRepository = chargePointRepository;
        this.userService = userService;
    }

    @Override
    public void createFeedback(FeedbackDTO feedbackDTO) {
        User currentUser = userService.getCurrentUser();
        ChargePoint chargePoint = chargePointRepository.findById(feedbackDTO.getChargePointId()).orElseThrow(IllegalAccessError::new);
        Feedback feedback = new Feedback();
        feedback.setChargePoint(chargePoint);
        feedback.setUser(currentUser);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRate(feedbackDTO.getRate());
        feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackDTO> getFeedbackListByChargePoint(Long id) {
        return feedbackRepository.getFeedbackListByChargePointId(id)
                .stream().map(FeedbackDTO::toDTO)
                .collect(Collectors.toList());
    }
}
