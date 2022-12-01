package com.example.userms.service;

import com.example.userms.config.JwtTokenProvider;
import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.domain.dto.response.AuthCredential;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
