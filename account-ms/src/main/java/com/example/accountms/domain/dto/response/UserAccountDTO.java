package com.example.accountms.domain.dto.response;

import com.example.accountms.domain.enumeration.Currency;
import lombok.Data;

@Data
public class UserAccountDTO {
    private String accountNumber;
    private String balance;
    private Currency currency;
}
