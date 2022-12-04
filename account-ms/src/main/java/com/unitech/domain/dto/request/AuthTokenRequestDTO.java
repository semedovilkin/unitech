package com.unitech.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthTokenRequestDTO implements Serializable {
    private String jwtToken;
}