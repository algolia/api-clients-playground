import Foundation
import AlgoliaSearchClientSwift

let client = Client(appID: "APPID", apiKey: "APIKEY")
let index = client.index(withName: "INDEX NAME")

do {
  let results = try index.search(query: "")
  print(results)
} catch let error {
  print(error)
}
