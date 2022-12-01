package com.example.accountms.service.impl;

import com.example.accountms.domain.dto.response.AuthCredential;
import com.example.accountms.domain.dto.response.UserAccountDTO;
import com.example.accountms.domain.entity.User;
import com.example.accountms.domain.entity.UserAccount;
import com.example.accountms.domain.mapper.UserMapper;
import com.example.accountms.domain.repository.UserRepository;
import com.example.accountms.service.AuthService;
import com.example.accountms.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<UserAccountDTO> getAll(String token) {
        AuthCredential tokenCredential = authService.getPinIfTokenIsValid(token);
        User user = userRepository.findByPin(tokenCredential.getPin());
        List<UserAccount> accounts = user.getAccounts();
        return UserMapper.INSTANCE.toDtoList(accounts);
    }
}
