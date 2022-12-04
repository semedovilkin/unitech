package com.unitech.service;

import com.unitech.client.ExchangeClient;
import com.unitech.domain.dto.CurrencyResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private ExchangeClient client;

    @Test
    void exchange() {
        CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto(new BigDecimal("1.7"));
        when(client.exchange("AZN", "USD")).thenReturn(currencyResponseDto);

        assertThat(currencyService.exchange("AZN", "USD")).isEqualTo(new BigDecimal("1.7"));
    }
}