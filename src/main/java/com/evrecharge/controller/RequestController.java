package com.evrecharge.controller;

import java.util.Map;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.entity.Request;
import com.evrecharge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/{id}")
    public String welcome(Model model, @PathVariable("id") Long id) {
        model.addAttribute("point", requestService.getRentInfo(id));
        return "rent";
    }
    @PostMapping(value = "/request")
    public String create(@ModelAttribute("point")ChargePointInfoDTO chargePointInfoDTO) {
        System.out.println(chargePointInfoDTO.getTime());
        return "rent";
    }

    @GetMapping(value = "/price")
    @ResponseBody
    public String getPrice(@RequestParam("type")String type, @RequestParam("duration")String dueration){
        return "test";
    }

}