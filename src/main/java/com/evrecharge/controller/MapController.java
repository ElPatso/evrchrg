package com.evrecharge.controller;

import com.evrecharge.dto.AddressDTO;
import com.evrecharge.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MapController {

    private MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String map(){
        return "map";
    }

    @GetMapping("/map/address")
    @ResponseBody
    public List<AddressDTO> getAddresses(){
        return mapService.getAddresses();
    }
}
