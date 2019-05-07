# About Algolia's Symfony bundle

Algolia Symfony bundle was created by, and is maintained by [Algolia](https://github.com/algolia).

## Get started locally
> requires :
* [Composer](https://getcomposer.org/)
* [PHP 7.1.3+](https://www.php.net/releases/index.php)

```bash
# Install from Algolia's Playground from Github
git clone https://github.com/algolia/api-clients-playground

# Move to Laravel-scout-extended folder.
cd api-clients-playground/symfony

# Install dependencies
composer install

# Add your credential in .env
ALGOLIA_APP_ID='YOUR APPLICATION ID'
ALGOLIA_SECRET='YOUR ADMIN API KEY'

# Your database doesn't exists, so you just need to create one and load data then send it to Algolia
composer update

# See the search query
php bin/console server:run
```

> See the result of the query here : [localhost](http://127.0.0.1:8000)
> Perform search queries with the `query` param : http://127.0.0.1:8000?query=foo




