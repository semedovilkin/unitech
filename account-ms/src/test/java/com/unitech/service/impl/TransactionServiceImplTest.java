package com.unitech.service.impl;

import com.unitech.domain.dto.request.TransferToAccountDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.domain.dto.response.TransactionResponseDTO;
import com.unitech.domain.entity.Account;
import com.unitech.domain.entity.Customer;
import com.unitech.domain.entity.Transaction;
import com.unitech.domain.enumeration.Operation;
import com.unitech.domain.repository.AccountRepository;
import com.unitech.domain.repository.TransactionRepository;
import com.unitech.exception.InSufficientFundException;
import com.unitech.exception.InValidSenderAccountException;
import com.unitech.exception.InValidTransferException;
import com.unitech.service.AccountService;
import com.unitech.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private AuthService authService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountService accountService;

    private Customer customer;
    private TransferToAccountDTO transferToAccountDTO;
    private TransactionResponseDTO transactionResponse;
    private Account senderAccount;
    private Account receiverAccount;
    private AuthCredential authCredential;

    @BeforeEach
    void setUp() {
        authCredential = new AuthCredential(true, "63ZZZ5Z");

        transferToAccountDTO = new TransferToAccountDTO()
                .setSenderAccount(1)
                .setReceiverAccount(2)
                .setAmount(new BigDecimal(10));

        transactionResponse = new TransactionResponseDTO()
                .setAccountNumber(1)
                .setAmount(new BigDecimal(10))
                .setOperation(Operation.OUTGOING);


        senderAccount = new Account()
                .setId(1)
                .setAccountNo(1)
                .setBalance(new BigDecimal(10))
                .setActive(true);

        receiverAccount = new Account()
                .setId(5)
                .setAccountNo(2)
                .setBalance(new BigDecimal(0))
                .setActive(true);

        customer = new Customer()
                .setId(1)
                .setPin("63ZZZ5Z");
    }

    @Test
    void givenInValidPinWhenAccount2AccountThenThrowInValidSenderAccountException() {
        //arrange
        when(authService.getPinIfTokenIsValid(anyString())).thenReturn(authCredential);
        when(accountService.findByAccountNumber(1)).thenReturn(senderAccount.setCustomer(customer.setPin("test")));
        when(accountService.findByAccountNumber(2)).thenReturn(receiverAccount);
        //assert
        assertThrows(InValidSenderAccountException.class, () -> transactionService.account2Account(anyString(), transferToAccountDTO));
    }

    @Test
    void givenSameAccountsWhenAccount2AccountThenThrowInValidTransferException() {
        //given
        transferToAccountDTO.setReceiverAccount(1);
        //arrange
        when(authService.getPinIfTokenIsValid(anyString())).thenReturn(authCredential);
        when(accountService.findByAccountNumber(1)).thenReturn(senderAccount.setCustomer(customer));
        //assert
        assertThrows(InValidTransferException.class, () -> transactionService.account2Account(anyString(), transferToAccountDTO));
    }

    @Test
    void givenInValidPinWhenAccount2AccountThenThrowInSufficientFundException() {
        //given
        senderAccount.setBalance(new BigDecimal(2));
        //Arrange
        when(authService.getPinIfTokenIsValid(anyString())).thenReturn(authCredential);
        when(accountService.findByAccountNumber(1)).thenReturn(senderAccount.setCustomer(customer));
        when(accountService.findByAccountNumber(2)).thenReturn(receiverAccount);
        //Assert
        assertThrows(InSufficientFundException.class, () -> transactionService.account2Account(anyString(), transferToAccountDTO));
    }

    @Test
    void givenInValidPinWhenAccount2AccountThenTransferResponseDTO() {
        //Arrange
        when(authService.getPinIfTokenIsValid(anyString())).thenReturn(authCredential);
        when(accountService.findByAccountNumber(1)).thenReturn(senderAccount.setCustomer(customer));
        when(accountService.findByAccountNumber(2)).thenReturn(receiverAccount);
        when(accountRepository.save(any())).thenReturn(new Account());
        when(transactionRepository.save(any())).thenReturn(new Transaction());
        //Assert
        assertThat(transactionService.account2Account(anyString(), transferToAccountDTO)).isEqualTo(transactionResponse);
    }
}