package com.unitech.service;

import com.unitech.domain.dto.response.AuthCredential;

public interface AuthService {
    AuthCredential getPinIfTokenIsValid(String token);
}
