package com.example.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Value;

@Introspected
@Value
public class CreateWallet {
    String name;
}
