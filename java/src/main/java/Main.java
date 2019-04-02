import com.algolia.search.*;
import com.algolia.search.models.indexing.BatchIndexingResponse;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.algolia.search.models.settings.IndexSettings;
import com.algolia.search.models.settings.SetSettingsResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

  private static String ALGOLIA_APPLICATION_ID_1 = System.getenv("ALGOLIA_APPLICATION_ID_1");
  private static String ALGOLIA_API_KEY_1 = System.getenv("ALGOLIA_ADMIN_KEY_1");

  public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

    // Create a searchClient
    SearchClient searchClient = new SearchClient(ALGOLIA_APPLICATION_ID_1, ALGOLIA_API_KEY_1);

    // Init an index
    SearchIndex<Employee> index =
        searchClient.initIndex("employees_" + System.getProperty("user.name"), Employee.class);

    List<Employee> employees = hireEmployees();
    CompletableFuture<BatchIndexingResponse> saveObjectsFuture = index.saveObjectsAsync(employees, true);

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

    CompletableFuture.allOf(searchAlgoliaFuture, searchAppleFuture, searchAmazonFuture);

    System.out.println(searchAlgoliaFuture.get().getHits().get(0).getName());
    System.out.println(searchAppleFuture.get().getHits().get(0).getName());
    System.out.println(searchAmazonFuture.get().getHits().get(0).getName());

    // You can close the underlying http client if you want to free resources.
    searchClient.close();
  }

  private static List<Employee> hireEmployees() {
    return Arrays.asList(
        new Employee("Algolia", "Julien Lemoine"),
        new Employee("Algolia", "Julien Lemoine"),
        new Employee("Amazon", "Jeff Bezos"),
        new Employee("Apple", "Steve Jobs"),
        new Employee("Apple", "Steve Wozniak"),
        new Employee("Arista Networks", "Jayshree Ullal"),
        new Employee("Google", "Lary Page"),
        new Employee("Google", "Rob Pike"),
        new Employee("Google", "Sergueï Brin"),
        new Employee("Microsoft", "Bill Gates"),
        new Employee("SpaceX", "Elon Musk"),
        new Employee("Tesla", "Elon Musk"),
        new Employee("Yahoo", "Marissa Mayer"));
  }
}
