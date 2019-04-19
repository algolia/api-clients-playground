import com.algolia.search.ConfigBase;
import com.algolia.search.HttpRequester;
import com.algolia.search.exceptions.AlgoliaRuntimeException;
import com.algolia.search.models.HttpRequest;
import com.algolia.search.models.HttpResponse;
import com.algolia.search.util.HttpStatusCodeUtils;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import org.asynchttpclient.*;

// Example of an HttpClient implementing HttpRequester
public final class AsyncHttpClientRequester implements HttpRequester {

  private final AsyncHttpClient asyncHttpClient;

  public AsyncHttpClientRequester(ConfigBase config) {

    Integer connectTimeOut = config.getConnectTimeOut();

    DefaultAsyncHttpClientConfig.Builder clientBuilder =
        Dsl.config()
            .setCompressionEnforced(true)
            .setConnectTimeout(connectTimeOut)
            .setKeepAlive(true);

    asyncHttpClient = Dsl.asyncHttpClient(clientBuilder);
  }

  /**
   * Sends the http request asynchronously to the API If the request is time out it creates a new
   * response object with timeout set to true Otherwise it throws a run time exception
   *
   * @param request the request to send
   * @throws AlgoliaRuntimeException When an error occurred processing the request on the server
   *     side
   */
  public CompletableFuture<HttpResponse> performRequestAsync(HttpRequest request) {
    BoundRequestBuilder requestToSend = buildRequest(request);
    return requestToSend
        .execute()
        .toCompletableFuture()
        .thenApplyAsync(this::buildResponse)
        .exceptionally(
            t -> {
              if (t.getCause() instanceof TimeoutException) {
                return new HttpResponse(true);
              }
              throw new AlgoliaRuntimeException(t);
            });
  }

  /**
   * Builds an Algolia response from the server response
   *
   * @param response The server response
   */
  private HttpResponse buildResponse(Response response) {
    if (HttpStatusCodeUtils.isSuccess(response.getStatusCode())) {
      return new HttpResponse(response.getStatusCode(), response.getResponseBodyAsStream());
    }
    return new HttpResponse(response.getStatusCode(), response.getResponseBody());
  }

  /**
   * Builds an http request from an AlgoliaRequest object
   *
   * @param algoliaHttpRequest The Algolia request object
   */
  private BoundRequestBuilder buildRequest(HttpRequest algoliaHttpRequest) {
    Request request =
        new RequestBuilder(algoliaHttpRequest.getMethod().toString())
            .setUrl(algoliaHttpRequest.getUri().toString())
            .setMethod(algoliaHttpRequest.getMethod().toString())
            .setSingleHeaders(algoliaHttpRequest.getHeaders())
            .setBody(algoliaHttpRequest.getBody())
            .setRequestTimeout(algoliaHttpRequest.getTimeout())
            .build();

    return asyncHttpClient.prepareRequest(request);
  }

  @Override
  public void close() throws IOException {
    asyncHttpClient.close();
  }
}
