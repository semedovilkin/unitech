package com.unitech.controller;

import com.unitech.domain.dto.request.TransferToAccountDTO;
import com.unitech.domain.dto.response.TransactionResponseDTO;
import com.unitech.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/account2account")
    public ResponseEntity<TransactionResponseDTO> transferAccountToAccount(
            @RequestHeader("auth_token") String token,
            @Valid  @RequestBody TransferToAccountDTO transferToAccountDTO) {
        return ResponseEntity.ok(transactionService.account2Account(token,transferToAccountDTO));
    }

}
