package com.unitech.controller;

import com.unitech.domain.dto.response.AccountDTO;
import com.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAll(@RequestHeader("auth_token") String token) {
        return ResponseEntity.ok(accountService.getAllOnlyActiveStates(token));
    }

}
