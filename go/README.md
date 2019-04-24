## Go setup

```
# Install go
brew install go

# Make sure that you are using the latest version of Go (at least 1.8 >=)
go version
```

## Fetch the client as a dependency

```
# Create a new module for the current project
go mod init playground

# Fetch the Go client as a dependency
go get github.com/algolia/algoliasearch-client-go@v3.0.0-beta05
```

## Compile and run

```
# Execute the main file by compiling then running the executable
go build
./playground

# Or directly compile it and run it at once
go run main.go
```
