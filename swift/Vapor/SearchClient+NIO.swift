//
//  SearchClient+NIO.swift
//
//
//  Created by Adam Eri (bimspot.io) on 11.09.20.
//

import AlgoliaSearchClient
import Foundation
import Vapor

extension Index {
  /// Add multiple schemaless objects to an index.
  ///
  /// - Parameters:
  ///   - objects: The list of records to save.
  ///   - autoGeneratingObjectID: Add objectID if record type doesn't provide
  ///    it in serialized form.
  ///   - requestOptions: Configure request locally with RequestOptions.
  ///   - eventLoop: The eventloop to run the operations on.
  /// - Returns: A future to resovlve when the operation completes.
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
        switch result {
        case .success:
          promise.succeed(())
        case .failure(let error):
          promise.fail(error)
        }
      })
    return promise.futureResult
  }

  /// Create or change an index’s Settings.
  /// Only non-null settings are overridden; null settings are left unchanged
  /// Performance wise, it’s better to setSettings before pushing the data.
  ///
  /// - Parameters:
  ///   - settings: The Settings to be set.
  ///   - resetToDefault: Reset a settings to its default value.
  ///   - forwardToReplicas: Whether to forward the same settings to the
  ///    replica indices.
  ///   - requestOptions: Configure request locally with RequestOptions.
  ///   - eventLoop: The eventloop to run the operations on.
  /// - Returns: A future to resovlve when the operation completes.
  func setSettings(
    _ settings: Settings,
    resetToDefault: [Settings.Key] = [],
    forwardToReplicas: Bool? = nil,
    requestOptions: RequestOptions? = nil,
    on eventLoop: EventLoop) -> Future<Void>
  {
    let promise = eventLoop.makePromise(of: Void.self)
    self.setSettings(
      settings,
      resetToDefault: resetToDefault,
      forwardToReplicas: forwardToReplicas,
      requestOptions: requestOptions,
      completion: { result in
        switch result {
        case .success:
          promise.succeed(())
        case .failure(let error):
          promise.fail(error)
        }
      })
    return promise.futureResult
  }

  /// Delete the index and all its settings, including links to its replicas.
  ///
  /// - Parameters:
  ///   - requestOptions: Configure request locally with RequestOptions.
  ///   - eventLoop: The eventloop to run the operations on.
  /// - Returns: A future to resovlve when the operation completes.
  func delete(
    requestOptions: RequestOptions? = nil,
    on eventLoop: EventLoop) -> Future<Void>
  {
    let promise = eventLoop.makePromise(of: Void.self)
    self.delete(completion: { result in
      switch result {
      case .success:
        promise.succeed(())
      case .failure(let error):
        promise.fail(error)
      }
    })
    return promise.futureResult
  }

  ///  Return whether an index exists or not.
  ///
  /// - Parameter eventLoop: The eventloop to run the operations on.
  func exists(on eventLoop: EventLoop) -> Future<Bool> {
    let promise = eventLoop.makePromise(of: Bool.self)
    self.exists(completion: { result in
      switch result {
      case .success:
        promise.succeed(())
      case .failure(let error):
        promise.fail(error)
      }
    })
    return promise.futureResult
  }
}
