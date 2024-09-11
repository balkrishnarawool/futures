# Futures

Futures is a `CompletableFuture` utility.

#### Type-aware `allOf()`

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

But `CompletableFutures.combine()` provides an alternative which has the results of `Future`s readily available and with correct types.
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

#### Combining two `CompletableFuture<Stream<T>>`s into one
```java
    var future1 = CompletableFuture.completedFuture(Stream.of(1,2,3,4));
    var future2 = CompletableFuture.completedFuture(Stream.of(5,6,7,8));
    
    var list = CompletableFutures.combine(future1, future2).join().collect(Collectors.toList());
```

#### Converting `Stream<SompletableFuture<T>>` into `CompletableFuture<Stream<T>>`
```java
    var stream = Stream.of(CompletableFuture.completedFuture(1),
            CompletableFuture.completedFuture(2),
            CompletableFuture.completedFuture(3),
            CompletableFuture.completedFuture(4));
    
    var future = CompletableFutures.sequence(stream);
    var list = future.join().collect(Collectors.toList());
```

#### Converting `Optional<SompletableFuture<T>>` into `CompletableFuture<Optional<T>>`
```java
    var optional = Optional.of(CompletableFuture.completedFuture(1));
    
    var future = CompletableFutures.sequence(optional);
    var value = future.join().orElse(0);
```

