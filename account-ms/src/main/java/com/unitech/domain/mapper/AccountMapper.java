package com.unitech.domain.mapper;


import com.unitech.domain.dto.response.AccountDTO;
import com.unitech.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDto(Account account);
}
