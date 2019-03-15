# About Algolia's API Client Python

Algolia's API Client Python was created by, and is maintained by [Algolia](https://github.com/algolia). The Algolia Search API Client for Python lets you easily use the Algolia Search REST API from your Python code.

## Get started locally

> **Requires:**
- **[Homebrew](https://brew.sh)**

First, use [Homebrew](https://brew.sh) to install Python 3.7:
```bash
# Install Python 3
brew install python3

# Create your Python project directory
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
import os

from algoliasearch.search_client import SearchClient
from algoliasearch.exceptions import AlgoliaException
from algoliasearch.responses import MultipleResponse

client = SearchClient.create(
    os.environ.get('ALGOLIA_APPLICATION_ID_1'),
    os.environ.get('ALGOLIA_ADMIN_KEY_1')
)

index = client.init_index('articles')

try:
    index.clear_objects().wait()
except AlgoliaException:  # Index not found
    pass

import asyncio

loop = asyncio.get_event_loop()

tasks = [
    index.save_object_async({'objectID': 1, 'foo': 'bar'}),
    index.save_object_async({'objectID': 2, 'foo': 'bar'}),
]

MultipleResponse(
    loop.run_until_complete(asyncio.gather(*tasks))
).wait()

result = loop.run_until_complete(index.search_async(''))

loop.close()

print(result)
```
