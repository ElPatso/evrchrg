package com.evrecharge.controller;

import com.evrecharge.dto.CreditCardDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.dto.RentDTO;
import com.evrecharge.service.ChargePointService;
import com.evrecharge.service.CreditCardService;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RentController {

    private ChargePointService chargePointService;
    private CreditCardService creditCardService;

    @Autowired
    public RentController(ChargePointService requestService,
                          CreditCardService creditCardService) {
        this.chargePointService = requestService;
        this.creditCardService = creditCardService;
    }


    @GetMapping(value = "/{id}")
    public String welcome(Model model, @PathVariable("id") Long id) {
        model.addAttribute("point", chargePointService.getRentInfo(id));
        return "rent";
    }

    @GetMapping(value = "/credit-card-info")
    @ResponseBody
    public CreditCardDTO getCreditCardByCurrentUser() {
        return creditCardService.getByCurrentUser();

    }

    @PostMapping(value = "/book")
    @ResponseBody
    public String create(@RequestBody RentDTO rentDTO) throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException {
        chargePointService.bookChargePoint(rentDTO);
        return "/";
    }

    @GetMapping(value = "/price")
    @ResponseBody
    public PriceDTO getPrice(@RequestParam("type") String type, @RequestParam("duration") Integer duration) {
        return chargePointService.calculatePrice(type, duration);
    }

}