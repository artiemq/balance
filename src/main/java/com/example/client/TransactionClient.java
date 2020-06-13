package com.example.client;

import com.example.resources.TransactionResource;
import io.micronaut.http.client.annotation.Client;

@Client("/transaction")
public interface TransactionClient extends TransactionResource {
}
