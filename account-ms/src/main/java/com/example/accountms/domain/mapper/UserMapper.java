package com.example.accountms.domain.mapper;


import com.example.accountms.domain.dto.response.UserAccountDTO;
import com.example.accountms.domain.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserAccountDTO> toDtoList(List<UserAccount> userAccounts);
}
