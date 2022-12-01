package com.example.userms.util.converters.impl;

import com.example.userms.domain.dto.request.UserRequestDTO;
import com.example.userms.domain.entity.User;
import com.example.userms.util.converters.UserRequestConverter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConverterImpl implements UserRequestConverter {
    @Override
    public User convert(UserRequestDTO item) {
        User user = new User();
        user.setPin(item.getPin());
        user.setPassword(item.getPassword());
        return user;
    }

    @Override
    public boolean validate(UserRequestDTO item) {
        return item != null;
    }
}
