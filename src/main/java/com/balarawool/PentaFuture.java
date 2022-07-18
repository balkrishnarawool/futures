package com.balarawool;

import java.util.concurrent.CompletableFuture;

public class PentaFuture<A, B, C, D, E> {

    private CompletableFuture<A> aFuture;
    private CompletableFuture<B> bFuture;
    private CompletableFuture<C> cFuture;
    private CompletableFuture<D> dFuture;
    private CompletableFuture<E> eFuture;

    PentaFuture(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture, CompletableFuture<D> dFuture, CompletableFuture<E> eFuture) {
        this.aFuture = aFuture;
        this.bFuture = bFuture;
        this.cFuture = cFuture;
        this.dFuture = dFuture;
        this.eFuture = eFuture;
    }

    public <T> CompletableFuture<T> thenApply(PentaFunction<A, B, C, D, E, T> f) {
        return CompletableFuture.allOf(aFuture, bFuture, cFuture, dFuture)
                .thenApply(ignored -> f.apply(aFuture.join(), bFuture.join(), cFuture.join(), dFuture.join(), eFuture.join()));
    }
}