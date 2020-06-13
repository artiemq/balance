package com.example.resources;


import com.example.model.CreateWallet;
import com.example.model.DepositRequest;
import com.example.model.ExchangeRequest;
import com.example.model.Wallet;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface WalletResources {
    @Post
    Wallet create(@Valid @NotNull @Body CreateWallet createWallet);

    @Post("/{name}/deposit")
    Wallet deposit(@NotBlank String name, @Body @Valid @NotNull DepositRequest depositRequest);

    @Get("/{name}")
    Wallet getWallet(@NotBlank String name);

    @Post("/exchange")
    Wallet exchange(@Valid @Body ExchangeRequest exchangeRequest);
}
