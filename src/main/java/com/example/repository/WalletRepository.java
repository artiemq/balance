package com.example.repository;

import com.example.entity.WalletEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface WalletRepository extends CrudRepository<WalletEntity, Integer> {

    @Query("select * from wallet where name = :from or name = :to for update")
    List<WalletEntity> getAndLockWalletsForExchange(@Nonnull String from, @Nonnull String to);

    @Query("select * from wallet where name = :walletName for update")
    Optional<WalletEntity> getAndLockWallet(@Nonnull String walletName);

    Optional<WalletEntity> findByName(@Nonnull String name);
}
