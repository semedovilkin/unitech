package com.unitech.service.impl;

import com.unitech.domain.dto.response.AccountDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.domain.entity.Account;
import com.unitech.domain.entity.Customer;
import com.unitech.domain.mapper.AccountMapper;
import com.unitech.domain.repository.AccountRepository;
import com.unitech.domain.repository.CustomerRepository;
import com.unitech.exception.AccountNotFoundException;
import com.unitech.exception.CustomerNotFoundException;
import com.unitech.service.AccountService;
import com.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AuthService authService;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public List<AccountDTO> getAllOnlyActiveStates(String token) {
        AuthCredential tokenCredential = authService.getPinIfTokenIsValid(token);

        Customer customer = customerRepository.findCustomerByPinIgnoreCase(tokenCredential.getPin())
                .orElseThrow(() -> new CustomerNotFoundException(tokenCredential.getPin()));

        return accountRepository.findAllByCustomerAndActive(customer, true)
                .stream().map(AccountMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public Account findByAccountNumber(Integer accountNumber) {
       return accountRepository.findAccountByAccountNo(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }
}
