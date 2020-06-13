package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Value;

import java.math.BigInteger;

@Introspected
@Value
public class Wallet {
    @JsonProperty("name")
    String name;

    @JsonProperty("balance")
    BigInteger balance;
}
