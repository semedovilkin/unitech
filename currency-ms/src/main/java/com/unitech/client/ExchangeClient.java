package com.unitech.client;

import com.unitech.domain.dto.CurrencyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-v1", url = "${feign.client.config.exchange.url}")
public interface ExchangeClient {

    @GetMapping(value = "/{from}/{to}", consumes = MediaType.APPLICATION_JSON_VALUE)
    CurrencyResponseDto exchange(@PathVariable("from") String from, @PathVariable("to") String to);

}

