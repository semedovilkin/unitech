package com.unitech.service.impl;

import com.unitech.config.JwtTokenProvider;
import com.unitech.domain.dto.AuthTokenDTO;
import com.unitech.domain.dto.request.UserRequestDTO;
import com.unitech.domain.entity.Roles;
import com.unitech.domain.entity.User;
import com.unitech.domain.enumeration.UserStatusesEnum;
import com.unitech.domain.mapper.UserMapper;
import com.unitech.domain.models.CustomUserException;
import com.unitech.domain.repository.UserRepository;
import com.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void checkPinIsTaken(String pin) {
        checkUserPinExistCondition(userRepository.findByPin(
                pin,
                UserStatusesEnum.DELETED));
    }

    @Override
    public void registerUser(UserRequestDTO userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        checkPinIsTaken(userRequestDto.getPin());
        User user = Optional.ofNullable(UserMapper.INSTANCE.toEntity(userRequestDto))
                .orElseThrow(() -> new IllegalArgumentException("Validation of request failed."));
        user.setStatus(UserStatusesEnum.ACTIVE);
        user.setRoles(Roles.mockRoleByUser(user));
        userRepository.save(user);
    }

    @Override
    public AuthTokenDTO signIn(UserRequestDTO userRequestDTO) {
        User user = getUserByPin(userRequestDTO.getPin());
        checkPin(user);
        checkPassword(user, userRequestDTO);
        final String jwtToken = tokenProvider.generateToken(userRequestDTO.getPin());
        return new AuthTokenDTO(jwtToken);
    }

    @Override
    public User getUserByPin(String pin) {
        return userRepository.findByPin(pin, UserStatusesEnum.DELETED);
    }

    private void checkUserPinExistCondition(User user) {
        if (user != null)
            throw new CustomUserException(409, "User's pin has been taken");
    }

    private void checkPin(User user) {
        if (user == null)
            throw new CustomUserException(404, "Pin is incorrect");
    }

    private void checkPassword(User user, UserRequestDTO userRequestDTO) {
        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword()))
            throw new CustomUserException(400, "Password is incorrect");
    }
}
