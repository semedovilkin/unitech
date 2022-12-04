package com.unitech.controller;

import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/check/jwtToken")
    public ResponseEntity<AuthCredential> checkJwtToken(@Valid @RequestBody AuthTokenDTO authToken) {
        return authService.getCredentials(authToken);
    }
}
