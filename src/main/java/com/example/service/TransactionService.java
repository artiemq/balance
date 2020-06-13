package com.example.service;

import com.example.entity.TransactionType;
import com.example.model.TransactionDto;
import com.example.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Singleton
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<TransactionDto> getTransactions(String walletName,
                                                TransactionType transactionType,
                                                LocalDateTime transactionTimeFrom,
                                                LocalDateTime transactionTimeTo) {
        return transactionRepository.findByCriteria(walletName, transactionType, transactionTimeFrom, transactionTimeTo)
                .stream()
                .map(it -> new TransactionDto(it.getTransactionType(), it.getCreatedAt(), it.getAmount()))
                .collect(Collectors.toList());
    }
}
