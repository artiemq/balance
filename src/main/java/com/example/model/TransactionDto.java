package com.example.model;

import com.example.entity.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Value;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Introspected
@Value
public class TransactionDto {
    @JsonProperty("transactionType")
    TransactionType transactionType;

    @JsonProperty("transactionTime")
    LocalDateTime transactionTime;

    @JsonProperty("amount")
    BigInteger amount;
}
