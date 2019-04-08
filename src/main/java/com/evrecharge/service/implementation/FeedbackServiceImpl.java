package com.evrecharge.service.implementation;

import com.evrecharge.dto.FeedbackDTO;
import com.evrecharge.entity.ChargePoint;
import com.evrecharge.entity.Feedback;
import com.evrecharge.entity.Request;
import com.evrecharge.entity.User;
import com.evrecharge.repository.ChargePointRepository;
import com.evrecharge.repository.FeedbackRepository;
import com.evrecharge.repository.RequestRepository;
import com.evrecharge.service.FeedbackService;
import com.evrecharge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final RequestRepository requestRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               UserService userService,
                               RequestRepository requestRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
        this.requestRepository = requestRepository;
    }

    @Transactional
    @Override
    public void createFeedback(FeedbackDTO feedbackDTO) {
        User currentUser = userService.getCurrentUser();
        Request request = requestRepository.findById(feedbackDTO.getChargePointId()).orElseThrow(IllegalAccessError::new);
        Feedback feedback = new Feedback();
        feedback.setChargePoint(request.getPoint());
        feedback.setUser(currentUser);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRate(feedbackDTO.getRate());
        feedbackRepository.save(feedback);
    }

    @Transactional
    @Override
    public List<FeedbackDTO> getFeedbackListByChargePoint(Long id) {
        return feedbackRepository.getFeedbackListByChargePointId(id)
                .stream().map(FeedbackDTO::toDTO)
                .collect(Collectors.toList());
    }
}
