package com.example.accountms.service;

import com.example.accountms.domain.dto.response.UserAccountDTO;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> getAll(String pin);
}
