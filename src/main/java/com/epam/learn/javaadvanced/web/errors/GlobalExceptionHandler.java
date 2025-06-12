package com.epam.learn.javaadvanced.web.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException ex, ServerWebExchange exchange) {
        return Mono.just(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private int status;
        private String message;
    }
}
