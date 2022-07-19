# Futures

Futures is a `CompletableFuture` utility.

`CompletableFuture.allOf()` returns a `CompletableFuture<Void>`.
After that, in order to get individual future results, we would have to do `get()` or `join()` on them.
```java
        var intFuture = getIntFutureFromSomewhere();
        var stringFuture = getStringFutureFromSomewhere();
        var doubleFuture = getDoubleFutureFromSomewhere();
        var decimalFuture = getDecimalFutureFromSomewhere();

        var res = CompletableFuture.allOf(intFuture, stringFuture, doubleFuture, decimalFuture)
                    .thenApply(ignored -> {
                        var i = intFuture.join();
                        var s = stringFuture.join();
                        var d = doubleFuture.join();
                        var bd = decimalFuture.join();

                        return "Something with i, s, d, bd";
                    });
```

But `CompletableFutures.combine()` provides an alternative which has the results of `Future`s are readily available and with correct types.
```java
        var intFuture = getIntFutureFromSomewhere();
        var stringFuture = getStringFutureFromSomewhere();
        var doubleFuture = getDoubleFutureFromSomewhere();
        var decimalFuture = getDecimalFutureFromSomewhere();

        var res = CompletableFutures.combine(intFuture, stringFuture, doubleFuture, decimalFuture)
                    .thenApply((i, s, d, bd) -> {
                        // Use i, s, d, bd directly.
                        return "Something with i, s, d, bd";
                    });
```


