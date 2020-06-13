package com.example.repository;

import com.example.entity.TransactionEntity;
import com.example.entity.TransactionType;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    @Query("select * from transactions t inner join wallet w on t.wallet_id = w.id" +
            " where w.name = :walletName" +
            " and transaction_type = :transactionType" +
            " and created_at > :transactionTimeFrom" +
            " and created_at < :transactionTimeTo")
    List<TransactionEntity> findByCriteria(String walletName,
                                           TransactionType transactionType,
                                           LocalDateTime transactionTimeFrom,
                                           LocalDateTime transactionTimeTo);
}
