require 'algolia'

client = Algolia::Search::Client.create('YOUR_ALGOLIA_APP_ID', 'YOUR_ALGOLIA_ADMIN_KEY')
index = client.init_index('YOUR_INDEX_NAME')
index.save_object!({ name: 'test', data: 10, objectID: '111' })
res = index.search('test')
puts res
