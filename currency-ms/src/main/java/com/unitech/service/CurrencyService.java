package com.unitech.service;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {
    BigDecimal exchange(String from, String to);
}