package com.unitech.service.impl;

import com.unitech.config.JwtTokenProvider;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.response.AuthCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Spy
    private JwtTokenProvider jwtTokenProvider;

    private AuthTokenDTO authTokenDTO;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "JWTSuperSecretKeyForAuthentication");
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpirationInMs", 86400000L);
        authTokenDTO = new AuthTokenDTO(jwtTokenProvider.generateToken("63ZZZ5Z"));
    }

    @Test
    void getAuthCredentialWhenTokenIsValid() {
        //Act
        ResponseEntity<AuthCredential> responseDto = authService.getCredentials(authTokenDTO);
        //Assert
        assertTrue(Objects.requireNonNull(responseDto.getBody()).isSuccess());
    }

    @Test
    void getAuthCredentialWhenTokenIsInValid() {
        //Arrange
        when(jwtTokenProvider.validateToken(authTokenDTO.getJwtToken())).thenReturn(false);
        //Act
        ResponseEntity<AuthCredential> responseDto = authService.getCredentials(authTokenDTO);
        //Assert
        assertFalse(Objects.requireNonNull(responseDto.getBody()).isSuccess());
    }


}