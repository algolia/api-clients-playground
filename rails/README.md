# About Algolia's Rails integration

Algolia's Rails integration was created by, and is maintained by [Algolia](https://github.com/algolia).

## Get started locally

> **Requires:**
- **[Ruby v2.2.2 or newer](https://www.ruby-lang.org/en/)**
- **[Bundler](https://bundler.io/)**
- **[Rails](https://rubyonrails.org/)**

First, make sure you're using Rails v5.0.0
```bash
rvm gemset create rails5
rvm gemset use rails5
gem install rails -v 5.0.0
```

Then setup the project
```bash
# Go inside the playground Rails folder
cd api-clients-playground/rails

# Install dependencies
bundle install

# Change your credentials in config/initializers/algoliasearch.rb
AlgoliaSearch.configuration = {
    application_id: ENV['ALGOLIA_APPLICATION_ID'],
    api_key: ENV['ALGOLIA_API_KEY']
}

# Migrate DB
rake db:migrate

# See the DB with the `db/contacts.json`
rake db:seed

# Run the server
bin/rails server
```

You can now edit the `app/controllers/search_controller.rb` and see your changes reflected.

Happy coding!
