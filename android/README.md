# Algolia's Android client

> ⚠️ Our Android API client is legacy, and in maintenance mode only. We recommend using our [Kotlin API client](https://github.com/algolia/algoliasearch-client-kotlin) which is better suited for Android development.

## Install JDK 8 or above

First, you need to install **Java** on your machine. 
Download and install [JDK](https://openjdk.java.net/install/).

* Using brew: `brew install openjdk`
* Using [SDKMAN!](https://sdkman.io/install) (recommended but optional).

## Setup an IDE

You can use multiple IDEs with Kotlin, we recommend you -in that order- the following:

- [Android Studio](https://developer.android.com/studio)
- [Intelij IDEA](https://www.jetbrains.com/idea/download/) Community or Ultimate.
- [Visual Studio Code](https://code.visualstudio.com/)
    * Install ["Kotlin" extension](https://marketplace.visualstudio.com/items?itemName=fwcd.kotlin)
 
## How to use the project

This project aims to show how to use the Android API client, without the need of running of a device or emulator:

First, build the project

```shell
./gradlew assembleDebug
```

Then, run the project

```shell
./gradlew testDebug --rerun-tasks
```

_Code under `app/src/test/java/com/algolia/client/android/` will be executed._
