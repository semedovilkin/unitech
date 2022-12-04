package com.unitech.controller;

import com.unitech.domain.dto.request.UserRequestDTO;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO userRequestDto) {
        userService.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthTokenDTO> signIn(@Valid @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok(userService.signIn(userRequestDto));
    }
}