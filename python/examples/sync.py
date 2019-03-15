import os

from algoliasearch.search_client import SearchClient
from algoliasearch.exceptions import AlgoliaException

client = SearchClient.create(
    os.environ.get('ALGOLIA_APPLICATION_ID_1'),
    os.environ.get('ALGOLIA_ADMIN_KEY_1')
)

index = client.init_index('articles')

try:
    index.clear_objects().wait()
except AlgoliaException:  # Index not found
    pass

index.save_object({'objectID': 1, 'firstname': 'Jimmie', 'lastname': 'Barninger'}).wait()

print(index.search(''))
