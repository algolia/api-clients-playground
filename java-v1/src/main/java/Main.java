import com.algolia.search.saas.APIClient;
import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Index;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static String ALGOLIA_APPLICATION_ID_1 = System.getenv("ALGOLIA_APPLICATION_ID_1");
    private static String ALGOLIA_API_KEY_1 = System.getenv("ALGOLIA_ADMIN_KEY_1");

    public static void main(String[] args) throws AlgoliaException {

        APIClient client = new APIClient(ALGOLIA_APPLICATION_ID_1, ALGOLIA_API_KEY_1);
        Index index = client.initIndex("test");

        JSONObject settings = new JSONObject()
                .put("replicas", Arrays.asList("test_2", "test_3"));

        JSONObject res = index.setSettings(settings);
        System.out.println(res);

    }
}
