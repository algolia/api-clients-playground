import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

  static String ALGOLIA_APPLICATION_ID_1 = System.getenv("ALGOLIA_APPLICATION_ID_1");
  static String ALGOLIA_API_KEY_1 = System.getenv("ALGOLIA_ADMIN_KEY_1");

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, IOException {
    // Run the example with the default http client (apache)
    DefaultClientExample.run();

    // Run the example with a custom implementation of async http client (injected)
    CustomHttpClientExample.run();
  }
}
