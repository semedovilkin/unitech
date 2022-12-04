package com.unitech.controller;

import com.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency/")
@RequiredArgsConstructor
class CurrencyController {
    private final CurrencyService currencyService;


    @GetMapping("exchange/{from}/{to}")
    public BigDecimal exchange(@PathVariable String from,
                               @PathVariable String to){
        return currencyService.exchange(from, to);
    }
}