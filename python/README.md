# About Algolia's API Client Python

Algolia's API Client Python was created by, and is maintained by [Algolia](https://github.com/algolia). The Algolia Search API Client for Python lets you easily use the Algolia Search REST API from your Python code.

## Get started locally

> **Requires:**
- **[Homebrew](https://brew.sh)**

First, use [Homebrew](https://brew.sh) to install Python 3.7:
```bash
# Install Python 3
brew install python3

cd python

# Create a Python Virtual Environment inside your directory
python3 -m venv venv

# Activate the Python Virtual Environment
source venv/bin/activate

# At any time, use the following command to deactivate it
deactivate
```

Finally, install `algoliasearch` - API Client Python v2:
```
pip install https://github.com/algolia/algoliasearch-client-python/archive/develop.zip
```

### Synchronous example:

```py
import os

from algoliasearch.search_client import SearchClient
from algoliasearch.exceptions import AlgoliaException

client = SearchClient.create(
    os.environ.get('ALGOLIA_APPLICATION_ID_1'),
    os.environ.get('ALGOLIA_ADMIN_KEY_1')
)

index = client.init_index('articles')

index.save_objects([
    {'objectID': 1, 'firstname': 'Jimmie', 'lastname': 'Barninger'},
    {'objectID': 2, 'firstname': 'Warren', 'lastname': 'Speach'}
]).wait()

hits = index.search('Jimmie')

print(hits)
```

### Asynchronous example:

First, require asynchronous libraries:

```
pip install 'asyncio>=3.4,<4.0' 'aiohttp>=2.0,<4.0' 'async_timeout>=2.0,<4.0'
```


```py
import asyncio
import os

from algoliasearch.search_client import SearchClient
from algoliasearch.exceptions import AlgoliaException
from algoliasearch.responses import MultipleResponse

app_id = os.environ.get('ALGOLIA_APPLICATION_ID_1')
api_key = os.environ.get('ALGOLIA_ADMIN_KEY_1')


async def main():
    async with SearchClient.create(app_id, api_key) as client:
        index = client.init_index('articles')

        try:
            (await index.clear_objects_async()).wait()
        except AlgoliaException:  # Index not found
            pass

        results = await asyncio.gather(
            index.save_object_async({'objectID': 1, 'foo': 'bar'}),
            index.save_object_async({'objectID': 2, 'foo': 'foo'})
        )

        MultipleResponse(results).wait()

        print(await index.search_async(''))

asyncio.run(main())
```
