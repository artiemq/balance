package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "wallet_id")
    private Integer walletId;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private BigInteger amount;

    @Column(name = "created_at")
    @GeneratedValue
    private LocalDateTime createdAt;
}
