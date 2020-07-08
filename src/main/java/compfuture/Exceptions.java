package compfuture;

import java.util.concurrent.CompletableFuture;

public class Exceptions {
  public static void main(String[] args) {
    CompletableFuture<Void> cfv = CompletableFuture
        .supplyAsync(() -> Thread.currentThread().getName() + " Hello World")
        .thenApplyAsync(x -> {
          if (Math.random() > 0.5) {
            throw new RuntimeException("problem!");
          }
          return x.toUpperCase();
        })
        .thenApplyAsync(x -> "p1 " + x)
        .thenApplyAsync(x -> "p2 " + x)
        .handleAsync((d, t) -> {
          if (t == null) {
            return "Nonexeption processed -- " + d;
          } else {
            return "Ouch, there was an exception " + t.getMessage();
          }
        })
        .thenApplyAsync(x -> "p3 " + x)
        .thenAcceptAsync(x ->
            System.out.println(Thread.currentThread().getName() + ": " + x))
    ;
    System.out.println("Pipeline assembled (main exiting)...");
    cfv.join();
  }
}
