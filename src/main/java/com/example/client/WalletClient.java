package com.example.client;

import com.example.resources.WalletResources;
import io.micronaut.http.client.annotation.Client;

@Client("/wallet")
public interface WalletClient extends WalletResources {
}
