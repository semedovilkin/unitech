package com.example.userms.service;

import com.example.userms.config.JwtTokenProvider;
import com.example.userms.domain.dto.request.UserRequestDTO;
import com.example.userms.domain.dto.AuthTokenDTO;
import com.example.userms.domain.entity.User;
import com.example.userms.domain.enumeration.UserStatusesEnum;
import com.example.userms.domain.models.CustomUserException;
import com.example.userms.domain.repository.UserRepository;
import com.example.userms.util.converters.UserRequestConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRequestConverter userRequestConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void checkPinIsTaken(String pin) {
        User user = userRepository.findByPin(
                pin,
                UserStatusesEnum.DELETED);
        if (user != null) {
            throw new IllegalArgumentException("User's pin has been taken.");
        }
    }

    @Override
    public void registerUser(UserRequestDTO userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        checkPinIsTaken(userRequestDto.getPin());

        User user = userRequestConverter.convertSafely(userRequestDto)
                .orElseThrow(() -> new IllegalArgumentException("Validation of request failed."));
        user.setStatus(UserStatusesEnum.DEACTIVATED);
        userRepository.save(user);
    }

    @Override
    public AuthTokenDTO signIn(UserRequestDTO userRequestDTO) {

        User user = getUserByPin(userRequestDTO.getPin());
        if (user == null) {
            throw new CustomUserException(404, "Username is incorrect");
        }

        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
            throw new CustomUserException(400, "Password is incorrect");
        }
        final String jwtToken = tokenProvider.generateToken(userRequestDTO.getPin());
        return new AuthTokenDTO(jwtToken);
    }


    //    @Override
//    @Transactional
//    public void activateUser(UserActivationRequestDTO userActivationRequestDto) {
//        log.info("Activate user request received by id: {} and token: {}",
//                userActivationRequestDto.getId(), userActivationRequestDto.getActivationCode()
//        );
//        User user = userRepository.findById(userActivationRequestDto.getId())
//                .orElseThrow(() -> new NotFoundException("User not found"));
//        if (!user.getStatus().equals(UserStatusesEnum.DEACTIVATED)) {
//            throw new IllegalArgumentException("Invalid request ");
//        } else if (!user.getEmail().equals(userActivationRequestDto.getEmail())) {
//            throw new NotFoundException("User not found.");
//        } else if (!user.getActivationToken().equals(userActivationRequestDto.getActivationCode())) {
//            throw new IllegalArgumentException("Invalid request.");
//        }
//        Integer updatedRows = userRepository.updateStatus(user.getId(), UserStatusesEnum.ACTIVE);
//        if (updatedRows == 0) {
//            throw new NotFoundException("User not found.");
//        }
//    }
//
    @Override
    public User getUserByPin(String pin) {
        return userRepository.findByPin(pin, UserStatusesEnum.ACTIVE);
    }

//    @Override
//    public User getById(Long id) {
//        log.info("Get user by id: {} request received", id);
//        return userRepository.getOne(id);
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        log.info("Find user by id: {} request received", id);
//        return userRepository.findById(id);
//    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
