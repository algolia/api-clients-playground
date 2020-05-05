// swift-tools-version:5.2
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Playground",
    dependencies: [
      .package(name: "AlgoliaSearchClientSwift", url: "https://github.com/algolia/algoliasearch-client-swift", from: "8.0.0-beta.3")
    ],
    targets: [
        .target(
            name: "Playground",
            dependencies: ["AlgoliaSearchClientSwift"]),
        .testTarget(
            name: "PlaygroundTests",
            dependencies: ["Playground"]),
    ]
)
