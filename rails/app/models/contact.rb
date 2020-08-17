class Contact < ApplicationRecord
  include AlgoliaSearch

  algoliasearch do
    # Use all default configuration
  end
end
