package com.unitech.service.impl;

import com.unitech.config.JwtTokenProvider;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.request.UserRequestDTO;
import com.unitech.domain.entity.User;
import com.unitech.domain.models.CustomUserException;
import com.unitech.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private UserRepository userRepository;
    @Spy
    private PasswordEncoder passwordEncoder;

    private UserRequestDTO userRequestDto;
    private AuthTokenDTO authTokenDTO;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "JWTSuperSecretKeyForAuthentication");
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpirationInMs", 86400000L);
        authTokenDTO = new AuthTokenDTO(jwtTokenProvider.generateToken("63ZZZ5Z"));
        userRequestDto = new UserRequestDTO("63ZZZ5Z", "12356#9mks!@265levnAAA@");
    }


    @Test
    void registerWhenPinAlreadyIsTakenThenThrowCustomUserException() {
        //given
        User user = new User().setPin("63ZZZ5Z").setPassword("12356#9mks!@265levnAAA@");
        //Arrange
        when(userRepository.findByPin(any(), any())).thenReturn(user);
        //Assert
        assertThrows(CustomUserException.class, () -> userService.registerUser(userRequestDto));
    }
    //        assertFalse(Objects.requireNonNull(responseDto.getBody()).isSuccess());

    @Test
    void register() {
        //given
        User user = new User().setPin("63ZQQ5Z").setPassword("12356#9mks!@265levnAAA@");
        //Arrange
        when(userRepository.save(any())).thenReturn(user);
        //act
        userService.registerUser(userRequestDto);
        //Assert
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void signInWhenUserCredentialsAreTrueThenSuccessful() {
        //given
        User user = new User().setPin("63ZZZ5Z").setPassword("12356#9mks!@265levnAAA@");
        //Arrange
        when(userRepository.findByPin(any(), any())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        //Assert
        assertThat(userService.signIn(userRequestDto)).isEqualTo(authTokenDTO);
    }

    @Test
    void signInWhenUserPinIsNotTrueThenThrowCustomUserException() {
        //given
        User user = new User().setPin("63ZZZ5Z").setPassword("12356#9mks!@265levnAAA@");
        //Assert
        assertThrows(CustomUserException.class, () -> userService.signIn(userRequestDto));
    }

    @Test
    void signInWhenUserPassIsNotTrueThenThrowCustomUserException() {
        //given
        User user = new User().setPin("63ZZZ5Z").setPassword("12356#9mks!@265levnAAA@");
        //Arrange
        when(userRepository.findByPin(any(), any())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        //Assert
        assertThrows(CustomUserException.class, () -> userService.signIn(userRequestDto));
    }
}