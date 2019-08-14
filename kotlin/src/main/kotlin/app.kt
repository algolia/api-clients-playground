import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID

fun main() {
    val appID = ApplicationID("YourApplicationID")
    val apiKey = APIKey("YourAdminAPIKey")

    val client = ClientSearch(appID, apiKey)
}