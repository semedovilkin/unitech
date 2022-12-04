package com.unitech.domain.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class TransferToAccountDTO {
    @NotNull(message = "senderAccount is required")
    private Integer senderAccount;
    @NotNull(message = "receiverAccount is required")
    private Integer receiverAccount;
    @Min(value = 1,message = "Minimum amount must be 1")
    private BigDecimal amount;

}
