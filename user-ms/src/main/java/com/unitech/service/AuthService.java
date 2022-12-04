package com.unitech.service;

import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.response.AuthCredential;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthCredential> getCredentials(AuthTokenDTO authTokenDTO);
}
