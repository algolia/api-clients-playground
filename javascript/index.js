const algoliasearch = require('algoliasearch');

var applicationId = '';
var apiKey = '';
var indexName = '';

const client = algoliasearch(applicationId, apiKey);
const index = client.initIndex(indexName)

index.search({
  query: ''
  },
  (err, { hits } = {}) => {
    if (err) throw err;

    console.log(hits);
  }
);
