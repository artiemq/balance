package com.example.service;

import com.example.entity.TransactionEntity;
import com.example.entity.TransactionType;
import com.example.entity.WalletEntity;
import com.example.exception.DuplicateNameException;
import com.example.exception.NotEnough;
import com.example.exception.NotFoundException;
import com.example.model.CreateWallet;
import com.example.model.DepositRequest;
import com.example.model.ExchangeRequest;
import com.example.model.Wallet;
import com.example.repository.TransactionRepository;
import com.example.repository.WalletRepository;
import io.micronaut.data.exceptions.DataAccessException;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public Wallet create(@Nonnull CreateWallet createWalletRequest) {
        WalletEntity walletEntity = new WalletEntity()
                .setName(createWalletRequest.getName())
                .setBalance(BigInteger.ZERO);
        try {
            walletEntity = walletRepository.save(walletEntity);
        } catch (DataAccessException ex) {
            throw new DuplicateNameException();
        }
        return new Wallet(walletEntity.getName(), walletEntity.getBalance());
    }

    @Transactional
    public Wallet deposit(@Nonnull String name, DepositRequest depositRequest) {
        WalletEntity wallet = walletRepository.getAndLockWallet(name)
                .orElseThrow(NotFoundException::new);

        TransactionEntity from = new TransactionEntity()
                .setTransactionType(TransactionType.IN)
                .setAmount(depositRequest.getAmount())
                .setWalletId(wallet.getId());

        transactionRepository.save(from);
        BigInteger balance = wallet.getBalance();
        wallet.setBalance(balance.add(depositRequest.getAmount()));

        wallet = walletRepository.update(wallet);

        return new Wallet(wallet.getName(), wallet.getBalance());
    }

    public Wallet getWallet(@Nonnull String name) {
        WalletEntity walletEntity = walletRepository.findByName(name)
                .orElseThrow(NotFoundException::new);
        return new Wallet(walletEntity.getName(), walletEntity.getBalance());
    }

    @Transactional
    public Wallet exchange(ExchangeRequest exchangeRequest) {
        List<WalletEntity> wallets = walletRepository
                .getAndLockWalletsForExchange(exchangeRequest.getFrom(), exchangeRequest.getTo());

        WalletEntity fromWallet = wallets.stream()
                .filter(it -> it.getName().equals(exchangeRequest.getFrom()))
                .findAny()
                .orElseThrow(NotFoundException::new);

        WalletEntity toWallet = wallets.stream()
                .filter(it -> it.getName().equals(exchangeRequest.getTo()))
                .findAny()
                .orElseThrow(NotFoundException::new);

        if (fromWallet.getBalance().compareTo(exchangeRequest.getAmount()) < 0) {
            throw new NotEnough();
        }

        TransactionEntity fromTransaction = new TransactionEntity()
                .setTransactionType(TransactionType.OUT)
                .setAmount(exchangeRequest.getAmount())
                .setWalletId(fromWallet.getId());

        transactionRepository.save(fromTransaction);

        TransactionEntity toTransaction = new TransactionEntity()
                .setTransactionType(TransactionType.IN)
                .setAmount(exchangeRequest.getAmount())
                .setWalletId(toWallet.getId());

        transactionRepository.save(toTransaction);

        BigInteger fromBalance = fromWallet.getBalance();
        fromWallet.setBalance(fromBalance.subtract(exchangeRequest.getAmount()));
        fromWallet = walletRepository.update(fromWallet);

        BigInteger toBalance = toWallet.getBalance();
        toWallet.setBalance(toBalance.add(exchangeRequest.getAmount()));
        walletRepository.update(toWallet);

        return new Wallet(fromWallet.getName(), fromWallet.getBalance());
    }
}
