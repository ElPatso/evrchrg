package com.evrecharge.controller;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.service.ChargePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RentController {

    private ChargePointService chargePointService;

    @Autowired
    public RentController(ChargePointService requestService) {
        this.chargePointService = requestService;
    }


    @GetMapping(value = "/")
    public String login(Model model) {
        //model.addAttribute("point", chargePointService.getRentInfo(id));
        return "welcome";
    }

    @GetMapping(value = "/{id}")
    public String welcome(Model model, @PathVariable("id") Long id) {
        model.addAttribute("point", chargePointService.getRentInfo(id));
        return "rent";
    }

    @PostMapping(value = "/{id}")
    public String create(@ModelAttribute("point") ChargePointInfoDTO chargePointInfoDTO) {
        chargePointService.bookChargePoint(chargePointInfoDTO);
        return "rent";
    }

    @GetMapping(value = "/price")
    @ResponseBody
    public PriceDTO getPrice(@RequestParam("type") String type, @RequestParam("duration") Integer duration) {
        return chargePointService.calculatePrice(type, duration);
    }

}