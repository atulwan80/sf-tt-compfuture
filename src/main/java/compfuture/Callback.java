package compfuture;

import java.util.concurrent.CompletableFuture;

public class Callback {

  public static CompletableFuture<String> getFileContents(String filename) {
    CompletableFuture<String> result = new CompletableFuture<>();

    new Thread(()->{
      System.out.println("Background/OS task starting file read...");
      try {
        Thread.sleep(2000);
        String fileContents = "Contents of file called " + filename
            + " are many lines of interesting text!";
        result.complete(fileContents);
      } catch (InterruptedException ie) {

      }
    }).start();

    return result;
  }

  public static void main(String[] args) {
    CompletableFuture.supplyAsync(() -> "data.txt")
        .thenComposeAsync(fn -> getFileContents(fn))
        .thenApplyAsync(c -> "The file came back " + c)
        .thenAcceptAsync(s -> System.out.println(s))
        .join();
  }
}
