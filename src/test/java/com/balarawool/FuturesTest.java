package com.balarawool;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void testCombineCfStreams() {
        var future1 = CompletableFuture.completedFuture(Stream.of(1,2,3,4));
        var future2 = CompletableFuture.completedFuture(Stream.of(5,6,7,8));

        var list = CompletableFutures.combine(future1, future2)
                .join()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testSequence() {
        var stream = Stream.of(CompletableFuture.completedFuture(1),
                CompletableFuture.completedFuture(2),
                CompletableFuture.completedFuture(3),
                CompletableFuture.completedFuture(4));

        var future = CompletableFutures.sequence(stream);
        var list = future.join().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testSequenceOptional() {
        var optional = Optional.of(CompletableFuture.completedFuture(1));

        var future = CompletableFutures.sequence(optional);
        var value = future.join().orElse(0);
        System.out.println(value);
    }
}