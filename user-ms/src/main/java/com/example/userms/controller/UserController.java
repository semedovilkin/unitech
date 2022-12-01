package com.example.userms.controller;

import com.example.userms.domain.dto.request.UserRequestDTO;
import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.service.UserService;
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
//    @ApiOperation(value = "Sign in service for user")
    public ResponseEntity<AuthTokenDTO> signIn(@Valid @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok(userService.signIn(userRequestDto));
    }


}