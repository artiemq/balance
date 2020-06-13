package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Value;

import java.math.BigInteger;


@Value
@Introspected
public class DepositRequest {
    @JsonProperty("amount")
    BigInteger amount;
}
