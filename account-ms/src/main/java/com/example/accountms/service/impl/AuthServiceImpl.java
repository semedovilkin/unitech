package com.example.accountms.service.impl;

import com.example.accountms.client.AuthFeignClient;
import com.example.accountms.domain.dto.request.AuthTokenRequestDTO;
import com.example.accountms.domain.dto.response.AuthCredential;
import com.example.accountms.exception.InvalidTokenException;
import com.example.accountms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthFeignClient authClient;

    @Override
    public AuthCredential getPinIfTokenIsValid(String token) {
        AuthCredential credential = authClient.getCredential(new AuthTokenRequestDTO(token));
        if (!credential.isSuccess())
            throw new InvalidTokenException("Token is not valid");
        return credential;
    }
}
