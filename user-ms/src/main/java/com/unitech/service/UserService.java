package com.unitech.service;

import com.unitech.domain.dto.request.UserRequestDTO;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.entity.User;

public interface UserService {
    void checkPinIsTaken(String username);
    void registerUser(UserRequestDTO userRequestDto);
    User getUserByPin(String usernameOrEmail);
    AuthTokenDTO signIn(UserRequestDTO userRequestDTO);
}
