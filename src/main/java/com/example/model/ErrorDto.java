package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ErrorDto {
    @JsonProperty("errorCode")
    String errorCode;
}
