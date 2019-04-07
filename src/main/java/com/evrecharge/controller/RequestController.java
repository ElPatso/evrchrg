package com.evrecharge.controller;

import com.evrecharge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public String getSentRequests(Model model) {
        model.addAttribute("requests", requestService.getSentRequestsByCurrentUser());
        return "requests";
    }

    @GetMapping("/received")
    public String getReceivedRequests(Model model) {
        model.addAttribute("requests", requestService.getReceivedRequestsByCurrentUser());
        return "requests";
    }

    @GetMapping("/info/{id}")
    public String getRequestInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("info", requestService.getInfoById(id));
        return "info";
    }

    @GetMapping("/request/{id}")
    public String getRequestById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("info", requestService.getRequestDriverInfo(id));
        return "request";
    }

    @PutMapping("/status/{id}")
    @ResponseBody
    public String refuse(@PathVariable("id") Long id, @RequestBody Map<String, String> json) {
        requestService.changeStatus(json.get("status"), id);
        return "/received";
    }

}
