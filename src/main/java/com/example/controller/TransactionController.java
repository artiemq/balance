package com.example.controller;

import com.example.entity.TransactionType;
import com.example.model.TransactionDto;
import com.example.resources.TransactionResource;
import com.example.service.TransactionService;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller("/transaction")
public class TransactionController implements TransactionResource {
    private final TransactionService transactionService;

    @Override
    public List<TransactionDto> getTransactions(@NotNull String walletName,
                                                @Nullable TransactionType transactionType,
                                                @Nullable LocalDateTime transactionTimeFrom,
                                                @Nullable LocalDateTime transactionTimeTo) {
        return transactionService.getTransactions(
                walletName,
                transactionType,
                transactionTimeFrom,
                transactionTimeTo
        );
    }
}
