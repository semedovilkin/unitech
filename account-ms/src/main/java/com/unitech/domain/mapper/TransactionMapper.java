package com.unitech.domain.mapper;

import com.unitech.domain.dto.response.TransactionResponseDTO;
import com.unitech.domain.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "operation", target = "operation")
    @Mapping(source = "account.accountNo", target = "accountNumber")
    @Mapping(source = "createdDate", target = "transactionTime")
    TransactionResponseDTO toDto(Transaction transaction);
}
