package com.example.userms.controller;

import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.domain.dto.response.AuthCredential;
import com.example.userms.service.AuthService;
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
//    @ApiOperation(value = "Validate token service used for user roles and permissions, call from other microservices")
    public ResponseEntity<AuthCredential> checkJwtToken(@RequestBody AuthTokenDTO authToken) {
        return authService.getCredentials(authToken);
    }
}
