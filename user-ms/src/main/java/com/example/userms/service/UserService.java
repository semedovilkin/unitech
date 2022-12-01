package com.example.userms.service;

import com.example.userms.domain.dto.request.UserRequestDTO;
import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.domain.entity.User;

public interface UserService {
    void checkPinIsTaken(String username);

    void registerUser(UserRequestDTO userRequestDto);
//
//    void activateUser(UserActivationRequestDTO userActivationRequestDto);
//
    User getUserByPin(String usernameOrEmail);
    AuthTokenDTO signIn(UserRequestDTO userRequestDTO);
    //
//    //INTERNAL
//    User getById(Long id);
//
//    Optional<User> findById(Long id);
//
    User save(User user);
}
