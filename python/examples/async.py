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
