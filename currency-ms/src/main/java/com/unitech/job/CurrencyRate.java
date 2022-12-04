package com.unitech.job;

import com.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyRate {
    private final CurrencyService currencyService;

    @CacheEvict(value = "exchange-rate", key = "{#from,#to}")
    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void scheduleFixedRateTask() {
        log.info("Cache removed");
    }

}
