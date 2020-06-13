package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Value;

import java.math.BigInteger;

@Introspected
@Value
public class ExchangeRequest {
    @JsonProperty("from")
    String from;

    @JsonProperty("to")
    String to;

    @JsonProperty("amount")
    BigInteger amount;
}
