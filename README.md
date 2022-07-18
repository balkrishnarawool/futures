# Futures

Futures is a `CompletableFuture` utility.

`CompletableFuture.allOf()` returns a `CompletableFuture<Void>`.
After that in order to get individual future results, we would have to do `get()` or `join()`.

        var intFuture = CompletableFuture.completedFuture(1);
        var stringFuture = CompletableFuture.completedFuture("One");
        var doubleFuture = CompletableFuture.completedFuture(1.0);
        var decimalFuture = CompletableFuture.completedFuture(BigDecimal.ONE);

        var res = CompletableFuture.allOf(intFuture, stringFuture, doubleFuture, decimalFuture)
                    .thenApply(ignored -> {
                        var i = intFuture.join();
                        var s = stringFuture.join();
                        var d = doubleFuture.join();
                        var bd = decimalFuture.join();

                        return "Something with i, s, d, bd";
                    });

But `CompletableFutures.combine()` provides type-safe alternative.

var intFuture = CompletableFuture.completedFuture(1);
var stringFuture = CompletableFuture.completedFuture("One");
var doubleFuture = CompletableFuture.completedFuture(1.0);
var decimalFuture = CompletableFuture.completedFuture(BigDecimal.ONE);

        var res = CompletableFutures.combine(intFuture, stringFuture, doubleFuture, decimalFuture)
                    .thenApply((i, s, d, bd) -> {
                        // Use i, s, d, bd directly.
                        return "Something with i, s, d, bd";
                    });

