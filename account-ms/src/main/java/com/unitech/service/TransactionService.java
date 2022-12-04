package com.unitech.service;

import com.unitech.domain.dto.request.TransferToAccountDTO;
import com.unitech.domain.dto.response.TransactionResponseDTO;
import com.unitech.domain.entity.Account;

import java.math.BigDecimal;

public interface TransactionService {
    TransactionResponseDTO account2Account(String token, TransferToAccountDTO transferToAccountDTO);
    TransactionResponseDTO send(Account sender, Account receiver, BigDecimal amount);
}
