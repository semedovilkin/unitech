package com.unitech.service.impl;

import com.unitech.domain.dto.response.AccountDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.domain.entity.Account;
import com.unitech.domain.entity.Customer;
import com.unitech.domain.mapper.AccountMapper;
import com.unitech.domain.repository.AccountRepository;
import com.unitech.domain.repository.CustomerRepository;
import com.unitech.exception.AccountNotFoundException;
import com.unitech.service.AuthService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AuthService authService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void givenTokenWhenIsValidThenThrowAccountList() {
        //given
        AuthCredential authCredential = new AuthCredential(true, "63ZZZ5Z");
        Optional<Customer> customer = Optional.of(new Customer());
        Account account = new Account().setAccountNo(1111).setBalance(new BigDecimal(10));
        List<Account> accounts = Collections.singletonList(account);
        List<AccountDTO> response = Collections.singletonList(AccountMapper.INSTANCE.toDto(account));
        //Arrange
        when(authService.getPinIfTokenIsValid(anyString())).thenReturn(authCredential);
        when(customerRepository.findCustomerByPinIgnoreCase(anyString())).thenReturn(customer);
        when(accountRepository.findAllByCustomerAndActive(any(), anyBoolean())).thenReturn(accounts);
        //assert
        assertThat(accountService.getAllOnlyActiveStates(anyString())).isEqualTo(response);
    }

    @Test
    void givenAccountNoWhenIsPresentThenReturn(){
        when(accountRepository.findAccountByAccountNo(anyInt())).thenReturn(Optional.of(new Account()));
        AssertionsForClassTypes.assertThat(accountService.findByAccountNumber(anyInt())).isEqualTo(new Account());
    }

    @Test
    void givenAccountNoWhenIsNotPresentThenThrowAccountNotFoundException(){
        assertThrows(AccountNotFoundException.class, () ->accountService.findByAccountNumber(anyInt()));
    }

}