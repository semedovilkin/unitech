package com.unitech.domain.mapper;

import com.unitech.domain.dto.request.UserRequestDTO;
import com.unitech.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO account);
}
