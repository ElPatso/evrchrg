package com.evrecharge.controller;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.CreditCardDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.dto.RentDTO;
import com.evrecharge.entity.CreditCard;
import com.evrecharge.service.ChargePointService;
import com.evrecharge.service.CreditCardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RentController {

    private ChargePointService chargePointService;
    private CreditCardservice creditCardservice;

    @Autowired
    public RentController(ChargePointService requestService,
                          CreditCardservice creditCardservice) {
        this.chargePointService = requestService;
        this.creditCardservice = creditCardservice;
    }


    @GetMapping(value = "/{id}")
    public String welcome(Model model, @PathVariable("id") Long id) {
        model.addAttribute("point", chargePointService.getRentInfo(id));
        return "rent";
    }

    @GetMapping(value = "/credit-card-info")
    @ResponseBody
    public CreditCardDTO getCreditCardByCurrentUser() {
        return creditCardservice.getByCurrentUser();

    }

    @PostMapping(value = "/book")
    @ResponseBody
    public String create(@RequestBody RentDTO rentDTO) {
        chargePointService.bookChargePoint(rentDTO);
        return "/login";
    }

    @GetMapping(value = "/price")
    @ResponseBody
    public PriceDTO getPrice(@RequestParam("type") String type, @RequestParam("duration") Integer duration) {
        return chargePointService.calculatePrice(type, duration);
    }

}