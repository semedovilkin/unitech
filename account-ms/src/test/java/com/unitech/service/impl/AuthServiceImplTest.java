package com.unitech.service.impl;

import com.unitech.client.AuthFeignClient;
import com.unitech.domain.dto.response.AuthCredential;
import com.unitech.exception.InvalidTokenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private AuthFeignClient authClient;

    @Test
    void givenTokenWhetIsValidThenReturnAuthCredential() {
        AuthCredential authCredential = new AuthCredential(true, "63ZZZ5Z");
        when(authClient.getCredential(any())).thenReturn(authCredential);
        assertThat(authService.getPinIfTokenIsValid(anyString())).isEqualTo(authCredential);
    }

    @Test
    void givenTokenWhetIsValidThenThrowInvalidTokenException() {
        AuthCredential authCredential = new AuthCredential(false, "63ZZZ5Z");
        when(authClient.getCredential(any())).thenReturn(authCredential);
        assertThrows(InvalidTokenException.class, () -> authService.getPinIfTokenIsValid(anyString()));

    }
}