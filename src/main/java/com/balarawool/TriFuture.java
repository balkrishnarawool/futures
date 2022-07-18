package com.balarawool;

import java.util.concurrent.CompletableFuture;

public class TriFuture<A, B, C> {

    private CompletableFuture<A> aFuture;
    private CompletableFuture<B> bFuture;
    private CompletableFuture<C> cFuture;

    TriFuture(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture) {
        this.aFuture = aFuture;
        this.bFuture = bFuture;
        this.cFuture = cFuture;
    }

    public <T> CompletableFuture<T> thenApply(TriFunction<A, B, C, T> f) {
        return CompletableFuture.allOf(aFuture, bFuture, cFuture)
                .thenApply(ignored -> f.apply(aFuture.join(), bFuture.join(), cFuture.join()));
    }
}