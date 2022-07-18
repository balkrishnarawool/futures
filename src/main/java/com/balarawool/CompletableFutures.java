package com.balarawool;

import java.util.concurrent.CompletableFuture;

public interface CompletableFutures {

    static <A, B, C> TriFuture<A, B, C> combine(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture) {
        return new TriFuture<>(aFuture, bFuture, cFuture);
    }

    static <A, B, C, D> QuadFuture<A, B, C, D> combine(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture, CompletableFuture<D> dFuture) {
        return new QuadFuture<>(aFuture, bFuture, cFuture, dFuture);
    }

    static <A, B, C, D, E> PentaFuture<A, B, C, D, E> combine(CompletableFuture<A> aFuture, CompletableFuture<B> bFuture, CompletableFuture<C> cFuture, CompletableFuture<D> dFuture, CompletableFuture<E> eFuture) {
        return new PentaFuture<>(aFuture, bFuture, cFuture, dFuture, eFuture);
    }
}