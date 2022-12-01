package com.example.accountms.service;

import com.example.accountms.domain.dto.response.AuthCredential;

public interface AuthService {
    AuthCredential getPinIfTokenIsValid(String token);
}
