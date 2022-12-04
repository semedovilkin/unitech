package com.unitech.service.impl;

import com.unitech.config.JwtTokenProvider;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<AuthCredential> getCredentials(AuthTokenDTO authTokenDTO) {
        String jwtToken = authTokenDTO.getJwtToken();
        if (jwtTokenProvider.validateToken(jwtToken)) {
            String pin = jwtTokenProvider.getPinFromJWT(jwtToken);
            return ResponseEntity.ok(new AuthCredential(Boolean.TRUE, pin));
        }
        return ResponseEntity.badRequest().body(new AuthCredential(Boolean.FALSE));
    }

}
