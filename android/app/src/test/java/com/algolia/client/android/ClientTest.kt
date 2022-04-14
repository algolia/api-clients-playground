package com.algolia.client.android

import com.algolia.search.saas.Client
import com.algolia.search.saas.Query
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ClientTest {
    @Test
    fun addition_isCorrect() {
        val client = Client("YourApplicationID", "YourSearchAPIKey")
        val index = client.getIndex("indexName")
        val query = Query().setQuery("")

        try {
            val response = index.search(query, null)
            println(response)
        } catch(exception: Exception) {
            println(exception)
        }
    }
}
