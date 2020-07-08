package compfuture;

import java.util.concurrent.CompletableFuture;

public class Example1 {
  public static void main(String[] args) {
    CompletableFuture<Void> cfv = CompletableFuture
        .supplyAsync(() -> Thread.currentThread().getName() + " Hello World")
        .thenApplyAsync(x -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ie) {
            return "Ouch that shouldn't happen!";
          }
          return x.toUpperCase();
        })
        .thenAcceptAsync(x ->
            System.out.println(Thread.currentThread().getName() + ": " + x))
    ;
    System.out.println("Pipeline assembled (main exiting)...");
    cfv.join();
  }
}
