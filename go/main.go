package main

import (
	"fmt"

	"github.com/algolia/algoliasearch-client-go/v3/algolia/opt"
	"github.com/algolia/algoliasearch-client-go/v3/algolia/search"
)

func main() {
	appID, apiKey, indexName := "", "", ""

	client := search.NewClient(appID, apiKey)
	index := client.InitIndex(indexName)

	books := []Book{
		{Author: "J.K. Rowling", Title: "Harry Potter and the Prizoner of Azkaban"},
		{Author: "Stephen King", Title: "It"},
	}

	res, err := index.SaveObjects(books, opt.AutoGenerateObjectIDIfNotExist(true))
	if err != nil {
		fmt.Printf("cannot save objects: %v\n", err)
		return
	}

	if err = res.Wait(); err != nil {
		fmt.Printf("error while waiting for save objects to complete: %v\n", err)
		return
	}
}

type Book struct {
	Author string `json:"author"`
	Title  string `json:"title"`
}
