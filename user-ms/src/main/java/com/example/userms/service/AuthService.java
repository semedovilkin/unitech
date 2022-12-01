package com.example.userms.service;

import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.domain.dto.response.AuthCredential;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthCredential> getCredentials(AuthTokenDTO authTokenDTO);
}
