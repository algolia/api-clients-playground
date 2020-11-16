# The Algolia Client In Vapor

## Futures

The Algolia Swift client uses callbacks for handling the asynchronous calls.
In Vapor (and in Swift NIO) it is important to receive the response on the
same `EventLoop` on which the operation was initiated on. Using callbacks for
this could be cumbersome.

A good solution for this is to utilise `Future`s by wrapping the original
client's methods in an `EventLoopPromise` using function overloading in an
extension.

The original:

```swift
func saveObjects<T: Encodable>(
  _ objects: [T],
  autoGeneratingObjectID: Bool = false,
  requestOptions: RequestOptions? = nil,
  completion: @escaping ResultBatchesCallback) -> Operation {}
```

The overload:

```swift
func saveObjects<T: Encodable>(
  _ objects: [T],
  autoGeneratingObjectID: Bool = false,
  requestOptions: RequestOptions? = nil,
  on eventLoop: EventLoop) -> Future<Void>
{
  let promise = eventLoop.makePromise(of: Void.self)
  self.saveObjects(
    objects,
    autoGeneratingObjectID: autoGeneratingObjectID,
    requestOptions: requestOptions,
    completion: { result in
      if case .success = result {
        promise.succeed(())
      } else if case .failure(let error) = result {
        promise.fail(error)
      } else {
        promise.fail(AlgoliaClientError.unknown)
      }
    })
  return promise.futureResult
}
```

See `SearchClient+NIO.swift` for a set of examples.

### Usage

The new overloads now expect the `EventLoop` and return a `Future`.

```swift
let algoliaIndex = self
  .searchClient
  .index(withName: "SomeIndex")

algoliaIndex
  .exists(on: self.client.eventLoop)
  .flatMap { exists in
    if exists {
      // do stuff
    }
  }
```

## Dependency Injection

Vapor 4 solves the DI problem very elegantly by adding extensions to the `Request`
object.

```swift
import AlgoliaSearchClient
import Foundation
import Vapor

/// The class wraps the Algolia client to be an injectable Vapor service.
final class Algolia {
  private let client: Client
  private let searchClient: SearchClient

  /// Creates and returns a
  /// - Parameter client: The Vapor client handling the incoming request.
  init(client: Client) {
    guard
      let appId: String = Environment.get("ALGOLIA_APP_ID"),
      let apiKey: String = Environment.get("ALGOLIA_API_KEY")
    else {
      log.error("ALGOLIA_APP_ID and/or ALGOLIA_API_KEY is missing from ENV")
      exit(9)
    }
    self.client = client
    self.searchClient = SearchClient(
      appID: ApplicationID(rawValue: appId),
      apiKey: APIKey(rawValue: apiKey))
  }
}

extension Request {
  var algolia: Algolia {
    .init(client: self.client)
  }
}
```

In the Controller then:

```swift
let index = self
  .searchClient
  .index(withName: "SomeIndex")

_ = req
  .algolia
  .update(index: index)
  .flatMapErrorThrowing { error in
    log.error("Failed to update the Algolia index", error)
  }
```
