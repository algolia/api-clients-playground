require 'algolia'

client = Algolia::Search::Client.create('ALGOLIA_APPLICATION_ID','ALGOLIA_ADMIN_KEY')
index = client.init_index('index_name')

res = index.search('')
puts res
