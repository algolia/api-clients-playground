const algoliasearch = require('algoliasearch');

var applicationId = '';
var apiKey = '';
var indexName = '';

const client = algoliasearch(applicationId, apiKey);
const index = client.initIndex(indexName);

(async function () {
    try {
        const results = await index.search();
        console.log(results);
    } catch (e) {
        console.log(e);
        throw e;
    }
})();

