import com.algolia.search.*;
import com.algolia.search.models.indexing.BatchIndexingResponse;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.algolia.search.models.settings.IndexSettings;
import com.algolia.search.models.settings.SetSettingsResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DefaultClientExample {

  public static void run() throws ExecutionException, InterruptedException, IOException {
    // Create a SearchClient (it's a Closeable, so you can leverage the try-with-resources
    // construction
    // to let the JVM close underlying resources when appropriate)
    try (SearchClient searchClient =
        DefaultSearchClient.create(Main.ALGOLIA_APPLICATION_ID_1, Main.ALGOLIA_API_KEY_1)) {

      // Init an index
      SearchIndex<Employee> index =
          searchClient.initIndex("employees_" + System.getProperty("user.name"), Employee.class);

      List<Employee> employees = Employee.hireEmployees();
      CompletableFuture<BatchIndexingResponse> saveObjectsFuture =
          index.saveObjectsAsync(employees, true);

      // Typed index
      IndexSettings settings =
          new IndexSettings()
              .setAttributesForFaceting(Collections.singletonList("searchable(company)"));

      // Typed request/response
      CompletableFuture<SetSettingsResponse> setSettingsFuture = index.setSettingsAsync(settings);

      // Calling get() is blocking on the future to be completed and waitTask is performing Algolia
      // wait operations
      saveObjectsFuture.get().waitTask();
      setSettingsFuture.get().waitTask();

      // Performing multiple typed search asynchronously
      CompletableFuture<SearchResult<Employee>> searchAlgoliaFuture =
          index.searchAsync(new Query("algolia"));

      SearchResult<Employee> search = index.search(new Query("algolia"));

      CompletableFuture<SearchResult<Employee>> searchAppleFuture =
          index.searchAsync(new Query("apple"));

      CompletableFuture<SearchResult<Employee>> searchAmazonFuture =
          index.searchAsync(new Query("amazon"));

      CompletableFuture.allOf(searchAlgoliaFuture, searchAppleFuture, searchAmazonFuture).join();

      System.out.println("Default client :" + search.getHits().get(0).getName());
      System.out.println("Default client :" + searchAlgoliaFuture.get().getHits().get(0).getName());
      System.out.println("Default client :" + searchAppleFuture.get().getHits().get(0).getName());
      System.out.println("Default client :" + searchAmazonFuture.get().getHits().get(0).getName());
    }
  }
}
