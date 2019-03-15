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
