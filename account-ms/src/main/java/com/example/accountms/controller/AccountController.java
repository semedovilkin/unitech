package com.example.accountms.controller;

import com.example.accountms.domain.dto.response.UserAccountDTO;
import com.example.accountms.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/all")
    public ResponseEntity<List<UserAccountDTO>> getAll(@RequestHeader("auth_token") String token) {
        List<UserAccountDTO> accounts = userAccountService.getAll(token);
        return ResponseEntity.ok(accounts);
    }
}
