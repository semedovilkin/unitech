package com.unitech.service;

import java.math.BigDecimal;

import com.unitech.client.ExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final ExchangeClient client;

    @Cacheable(value = "exchange-rate", key = "{#from,#to}")
    public BigDecimal exchange(String from, String to) {
        return client.exchange(from, to).getRate();
    }

}