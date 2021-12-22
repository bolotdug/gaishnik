package com.inovus.gaishnik.controller;

import com.inovus.gaishnik.service.CarNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarNumberController {

    private final CarNumberService carNumberService;

    @GetMapping("/random")
    public String random(){
        return carNumberService.random().toString();
    }

    @GetMapping("/next")
    public String next(){

        return carNumberService.next().toString();
    }


}
