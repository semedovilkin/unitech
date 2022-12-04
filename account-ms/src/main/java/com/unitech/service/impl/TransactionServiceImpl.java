package com.unitech.service.impl;

import com.unitech.domain.dto.request.TransferToAccountDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.domain.dto.response.TransactionResponseDTO;
import com.unitech.domain.entity.Account;
import com.unitech.domain.entity.Transaction;
import com.unitech.domain.enumeration.Operation;
import com.unitech.domain.mapper.TransactionMapper;
import com.unitech.domain.repository.AccountRepository;
import com.unitech.domain.repository.TransactionRepository;
import com.unitech.exception.InActiveAccountException;
import com.unitech.exception.InSufficientFundException;
import com.unitech.exception.InValidSenderAccountException;
import com.unitech.exception.InValidTransferException;
import com.unitech.service.AccountService;
import com.unitech.service.AuthService;
import com.unitech.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AuthService authService;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public TransactionResponseDTO account2Account(String token, TransferToAccountDTO transferToAccountDTO) {
        AuthCredential tokenCredential = authService.getPinIfTokenIsValid(token);

        Account sender =accountService.findByAccountNumber(transferToAccountDTO.getSenderAccount());
        Account receiver =accountService.findByAccountNumber(transferToAccountDTO.getReceiverAccount());

        checkSenderAccountIsValid(tokenCredential.getPin(), sender);
        checkTransactionAccounts(transferToAccountDTO);
        checkBalance(sender, transferToAccountDTO.getAmount());
        checkAccountStatus(sender,receiver);

        return send(sender, receiver, transferToAccountDTO.getAmount());
    }

    @Transactional
    public TransactionResponseDTO send(Account sender, Account receiver, BigDecimal amount) {

        BigDecimal subtracted = sender.getBalance().subtract(amount);
        sender.setBalance(subtracted);
        accountRepository.save(sender);

        receiver.setBalance(receiver.getBalance().add(amount));
        accountRepository.save(receiver);

        Transaction outgoingTransaction = new Transaction()
                .setAccount(sender)
                .setOperation(Operation.OUTGOING)
                .setAmount(amount);

        transactionRepository.save(outgoingTransaction);

        Transaction incomingTransaction = new Transaction()
                .setAccount(receiver)
                .setOperation(Operation.INCOMING)
                .setAmount(amount);

        transactionRepository.save(incomingTransaction);

        return TransactionMapper.INSTANCE.toDto(outgoingTransaction);
    }

    void checkSenderAccountIsValid(String senderPin, Account senderAccount) {
        if (!senderPin.equalsIgnoreCase(senderAccount.getCustomer().getPin())) {
            throw new InValidSenderAccountException(senderAccount.getAccountNo());
        }
    }

    void checkTransactionAccounts(TransferToAccountDTO transactionRequestDto) {
        if (transactionRequestDto.getSenderAccount().equals(transactionRequestDto.getReceiverAccount())) {
            throw new InValidTransferException();
        }
    }

    void checkBalance(Account sender, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InSufficientFundException();
        }
    }

    void checkAccountStatus(Account sender, Account receiver) {
        if (!sender.getActive()) {
            throw new InActiveAccountException(sender.getAccountNo());
        }
        if (!receiver.getActive()) {
            throw new InActiveAccountException(receiver.getAccountNo());
        }
    }

}
