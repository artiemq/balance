package com.example.resources;

import com.example.entity.TransactionType;
import com.example.model.TransactionDto;
import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Get;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface TransactionResource {
    @Get("/{walletName}{?transactionType,transactionTimeFrom,transactionTimeTo}")
    List<TransactionDto> getTransactions(
            @NotNull String walletName,
            @Nullable TransactionType transactionType,
            @Nullable @Format("yyyy-MM-dd HH:mm:ss") LocalDateTime transactionTimeFrom,
            @Nullable @Format("yyyy-MM-dd HH:mm:ss") LocalDateTime transactionTimeTo
    );
}
