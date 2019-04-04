package com.evrecharge.controller;

import com.evrecharge.dto.FeedbackDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FeedbackController {

    @GetMapping("/feedback")
    public String feedback(Model model){
        model.addAttribute("rating", new FeedbackDTO());
        return "feedback";
    }
}
