package com.unitech.domain.dto.response;

import com.unitech.domain.enumeration.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TransactionResponseDTO {
    private Operation operation;
    @JsonProperty("receiver_account_number")
    private Integer accountNumber;
    private BigDecimal amount;
    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;
}