package compfuture;

import java.util.concurrent.CompletableFuture;

public class AcceptEither {
  public static void delay() {
    try {
      Thread.sleep((int)(Math.random() * 3000) + 1000);
    } catch (InterruptedException ie) {

    }
  }
  public static void main(String[] args) {
    CompletableFuture<String> first =
        CompletableFuture.supplyAsync(() -> {
          delay();
          return "First";
        });
    CompletableFuture<String> second =
        CompletableFuture.supplyAsync(() -> {
          delay();
          return "Second";
        });

    first.acceptEither(second, s -> System.out.println("Message winner is " + s));

    CompletableFuture<String> nextStep =
        first.thenApplyAsync(s -> s.toUpperCase());
    nextStep.thenAccept(s -> System.out.println("nextStep accepted " + s));

    nextStep.thenRun(
        () -> System.out.println("nextStep completed, doing work after it"));

    nextStep.join();
    System.out.println("All finished...");
  }
}
