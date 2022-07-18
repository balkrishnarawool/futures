package com.balarawool;

import java.util.concurrent.CompletableFuture;

public class QuadFuture<A, B, C, D> {

    private CompletableFuture<A> aFuture;
    private CompletableFuture<B> bFuture;
    private CompletableFuture<C> cFuture;
    private CompletableFuture<D> dFuture;

    QuadFuture(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture, CompletableFuture<D> dFuture) {
        this.aFuture = aFuture;
        this.bFuture = bFuture;
        this.cFuture = cFuture;
        this.dFuture = dFuture;
    }

    public <T> CompletableFuture<T> thenApply(QuadFunction<A, B, C, D, T> f) {
        return CompletableFuture.allOf(aFuture, bFuture, cFuture, dFuture)
                .thenApply(ignored -> f.apply(aFuture.join(), bFuture.join(), cFuture.join(), dFuture.join()));
    }
}