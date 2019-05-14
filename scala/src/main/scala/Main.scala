import algolia.AlgoliaClient
import algolia.AlgoliaDsl._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App {

  val applicationId: String = System.getenv("ALGOLIA_APPLICATION_ID")
  val apiKey: String = System.getenv("ALGOLIA_ADMIN_API_KEY")

  val client = new AlgoliaClient(applicationId, apiKey)

  val indexing1 = client.execute {
    index into "contacts" `object` Contact("Jimmie", "Barninger", 93, "California Paint")
  }

  val indexing2 = client.execute {
    index into "contacts" `object` Contact("Warren", "Speach", 42, "Norwalk Crmc")
  }

  Await.ready(Future.sequence(Seq(indexing1, indexing2)), Duration.Inf)

  System.exit(0)
}
