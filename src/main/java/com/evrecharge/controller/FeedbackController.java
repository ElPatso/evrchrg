package com.evrecharge.controller;

import com.evrecharge.dto.FeedbackDTO;
import com.evrecharge.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback/{id}")
    public String feedback(Model model, @PathVariable("id") Long id) {
        FeedbackDTO feedback = new FeedbackDTO();
        feedback.setChargePointId(id);
        model.addAttribute("rating", feedback);
        return "feedback";
    }
    @PostMapping("/feedback")
    public String saveFeedback(@ModelAttribute("rating") FeedbackDTO feedbackDTO) {
        feedbackService.createFeedback(feedbackDTO);
        return "redirect:/";
    }


}
