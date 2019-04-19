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

public class CustomHttpClientExample {

  public static void run() throws ExecutionException, InterruptedException, IOException {

    // Search configuration
    SearchConfig config =
        new SearchConfig.Builder(Main.ALGOLIA_APPLICATION_ID_1, Main.ALGOLIA_API_KEY_1).build();

    // Creating a search client with another http client than the default one
    try (SearchClient searchClient =
        new SearchClient(config, new AsyncHttpClientRequester(config))) {

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

      System.out.println("Custom client :" + search.getHits().get(0).getName());
      System.out.println("Custom client :" + searchAlgoliaFuture.get().getHits().get(0).getName());
      System.out.println("Custom client :" + searchAppleFuture.get().getHits().get(0).getName());
      System.out.println("Custom client :" + searchAmazonFuture.get().getHits().get(0).getName());
    }
  }
}
