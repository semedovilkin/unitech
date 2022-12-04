package com.unitech.service;

import com.unitech.domain.dto.response.AccountDTO;
import com.unitech.domain.entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllOnlyActiveStates(String pin);
    Account findByAccountNumber(Integer accountNumber);
}
