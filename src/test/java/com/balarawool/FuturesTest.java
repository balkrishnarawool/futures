package com.balarawool;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

public class FuturesTest {

    @Test
    public void testSimpleFlow() {
        var intFuture = CompletableFuture.completedFuture(1);
        var stringFuture = CompletableFuture.completedFuture("One");
        var doubleFuture = CompletableFuture.completedFuture(1.0);
        var decimalFuture = CompletableFuture.completedFuture(BigDecimal.ONE);

        var res = CompletableFutures.combine(intFuture, stringFuture, doubleFuture, decimalFuture)
                .thenApply((i, s, d, bd) -> {
                    assertTrue(i instanceof Integer);
                    assertTrue(s instanceof String);
                    assertTrue(d instanceof Double);
                    assertTrue(bd instanceof BigDecimal);
                    return String.format("%d is int, %s is String, %f is double and %s is BigDecimal", i, s, d, bd);
                })
                .join();
        System.out.println(res);
    }
}