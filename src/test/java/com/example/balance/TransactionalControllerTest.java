package com.example.balance;

import com.example.client.TransactionClient;
import com.example.client.WalletClient;
import com.example.entity.TransactionType;
import com.example.model.CreateWallet;
import com.example.model.DepositRequest;
import com.example.model.ExchangeRequest;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MicronautTest
public class TransactionalControllerTest {
    @Inject
    WalletClient walletClient;

    @Inject
    TransactionClient transactionClient;

    @Test
    void shouldReturnTransactions() {
        var depositRequest = new DepositRequest(BigInteger.valueOf(100));

        var createWallet = new CreateWallet("wallet3");
        var wallet = walletClient.create(createWallet);
        walletClient.deposit(wallet.getName(), depositRequest);

        var createWalletSecond = new CreateWallet("huelet3");
        var walletSecond = walletClient.create(createWalletSecond);

        var exchangeRequest = new ExchangeRequest(wallet.getName(), walletSecond.getName(), BigInteger.valueOf(50));
        walletClient.exchange(exchangeRequest);

        var inTransactions = transactionClient.getTransactions(wallet.getName(),
                TransactionType.IN,
                LocalDateTime.now().minus(1, ChronoUnit.HOURS),
                LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        Assertions.assertEquals(1, inTransactions.size());

        var outTransactions = transactionClient.getTransactions(wallet.getName(),
                TransactionType.OUT,
                LocalDateTime.now().minus(1, ChronoUnit.HOURS),
                LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        Assertions.assertEquals(1, outTransactions.size());
    }
}
