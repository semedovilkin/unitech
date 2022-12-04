package com.unitech.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    @JsonProperty("account_number")
    private Integer accountNo;
    private BigDecimal balance;
}
