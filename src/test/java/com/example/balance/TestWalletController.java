package com.example.balance;

import com.example.client.WalletClient;
import com.example.model.CreateWallet;
import com.example.model.DepositRequest;
import com.example.model.ExchangeRequest;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigInteger;

@MicronautTest
public class TestWalletController {
    @Inject
    WalletClient walletClient;

    @Test
    void shouldCreateWallet() {
        var createWallet = new CreateWallet("wallet");

        var wallet = walletClient.create(createWallet);

        Assertions.assertEquals("wallet", wallet.getName());
    }

    @Test
    void shouldNotCreateWalletWithSameName() {
        var createWallet = new CreateWallet("wallet");
        walletClient.create(createWallet);

        Assertions.assertThrows(
                HttpClientResponseException.class,
                () -> walletClient.create(createWallet)
        );
    }

    @Test
    void shouldDeposit() {
        var createWallet = new CreateWallet("wallet2");
        var wallet = walletClient.create(createWallet);

        var depositRequest = new DepositRequest(BigInteger.valueOf(100));
        wallet = walletClient.deposit(wallet.getName(), depositRequest);

        Assertions.assertEquals(BigInteger.valueOf(100), wallet.getBalance());
    }

    @Test
    void shouldExchange() {
        var depositRequest = new DepositRequest(BigInteger.valueOf(100));

        var createWallet = new CreateWallet("wallet3");
        var wallet = walletClient.create(createWallet);
        walletClient.deposit(wallet.getName(), depositRequest);

        var createWalletSecond = new CreateWallet("huelet3");
        var walletSecond = walletClient.create(createWalletSecond);

        var exchangeRequest = new ExchangeRequest(wallet.getName(), walletSecond.getName(), BigInteger.valueOf(50));
        wallet = walletClient.exchange(exchangeRequest);

        Assertions.assertEquals(BigInteger.valueOf(50), wallet.getBalance());
        Assertions.assertEquals(BigInteger.valueOf(50), walletClient.getWallet(walletSecond.getName()).getBalance());
    }
}
