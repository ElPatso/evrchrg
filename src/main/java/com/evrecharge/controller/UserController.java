package com.evrecharge.controller;

import com.evrecharge.service.implementation.StripeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private StripeServiceImpl stripeServiceImpl;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
