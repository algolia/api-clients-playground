# Algolia's .NET console

## .NET environment setup

You can skip this part if you already have the .NET Core 7.x framework installed.

### .NET Core SDK 7.x

Download and install the .NET Core SDK from [.NET Downloads](https://dotnet.microsoft.com/en-us/download). 

If you have problems with the installation on MacOS, consult the [Known issues](https://github.com/dotnet/core/tree/main/release-notes/7.0) topic for the version you have installed.

To check if dotnet is correctly installed :

```shell
dotnet --version
```

You should have something like this depending on the version you have installed :

```shell
7.XX.XX
```

If you have the following message `dotnet command not found`. Please run the following command :

```shell
export PATH=/usr/local/share/dotnet:$PATH
```

### Visual studio Code

If you don't have it, you should download [Visual Studio Code](https://code.visualstudio.com/) which is in my opinion, the best .NET IDE for MacOS at the moment.

Once you have downloaded Visual Studio Code, I recommend you to install some usefull VSCode's plugins.

* [C# for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=ms-vscode.csharp)
* [.Net Core Test Explorer](https://marketplace.visualstudio.com/items?itemName=formulahendry.dotnet-test-explorer)

## How to use the project

This project aims to show how to use the .NET Algolia API Client from C# code.

Open your terminal in the `src/AlgoliaConsole` folder.

First, build the project

```shell
dotnet build
```

Then, run the project

```shell
ALGOLIA_APPLICATION_ID=<YOUR_APP_ID> ALGOLIA_ADMIN_API_KEY=<YOUR_API_KEY> dotnet run --project AlgoliaConsole
```
