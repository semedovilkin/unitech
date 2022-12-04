package com.unitech.service.impl;

import com.unitech.client.AuthFeignClient;
import com.unitech.domain.dto.request.AuthTokenRequestDTO;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.exception.InvalidTokenException;
import com.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthFeignClient authClient;

    @Override
    public AuthCredential getPinIfTokenIsValid(String token) {
        AuthCredential credential = authClient.getCredential(new AuthTokenRequestDTO(token));
        checkCredentialState(credential);
        return credential;
    }

    private void checkCredentialState(AuthCredential credential) {
        if (!credential.isSuccess())
            throw new InvalidTokenException("Token is not valid");
    }
}
