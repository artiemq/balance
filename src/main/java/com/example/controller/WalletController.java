package com.example.controller;

import com.example.model.CreateWallet;
import com.example.model.DepositRequest;
import com.example.model.ExchangeRequest;
import com.example.model.Wallet;
import com.example.resources.WalletResources;
import com.example.service.WalletService;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Controller("/wallet")
public class WalletController implements WalletResources {
    private final WalletService walletService;

    @Override
    public Wallet create(@Valid @NotNull CreateWallet createWallet) {
        return walletService.create(createWallet);
    }

    @Override
    public Wallet deposit(@NotBlank String name, @Valid @NotNull DepositRequest depositRequest) {
        return walletService.deposit(name, depositRequest);
    }

    @Override
    public Wallet getWallet(@NotBlank String name) {
        return walletService.getWallet(name);
    }

    @Override
    public Wallet exchange(@Valid ExchangeRequest exchangeRequest) {
        return walletService.exchange(exchangeRequest);
    }
}
