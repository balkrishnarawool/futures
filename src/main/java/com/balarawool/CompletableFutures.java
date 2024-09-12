package com.balarawool;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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

    // Copied from: https://stackoverflow.com/questions/48461315/how-to-transform-completablefuturestreamt-to-streamcompletablefuturet
    static <T> CompletableFuture<Stream<T>> sequence(Stream<CompletableFuture<T>> futures) {
        return futures
                .map(future -> future.thenApply(Stream::of))
                .reduce(CompletableFuture.completedFuture(Stream.empty()), CompletableFutures::combine);
    }

    static <T> CompletableFuture<Optional<T>> sequence(Optional<CompletableFuture<T>> optional) {
         return optional.map(future -> future.thenApply(Optional::of))
                 .orElse(CompletableFuture.completedFuture(Optional.empty()));
    }

    static <T> CompletableFuture<Stream<T>> combine(CompletableFuture<Stream<T>> f1, CompletableFuture<Stream<T>> f2) {
        return f1.thenCombine(f2, Stream::concat);
    }

}