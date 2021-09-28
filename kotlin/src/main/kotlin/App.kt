import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val appID = ApplicationID("YourApplicationID")
    val apiKey = APIKey("YourAdminAPIKey")
    val indexName = IndexName("YourIndexName")

    val client = ClientSearch(appID, apiKey)
    val index = client.initIndex(indexName)

    try {
        val response = index.search()
        println(response)
    } catch(exception: Exception) {
        println(exception)
    }
}
