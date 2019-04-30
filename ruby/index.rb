require 'algoliasearch'

Algolia.init(application_id: 'ALGOLIA_APP_ID',
             api_key:        'ALGOLIA_SECRET')
index = Algolia::Index.new('INDEX_NAME')

res = index.search('')
puts res
