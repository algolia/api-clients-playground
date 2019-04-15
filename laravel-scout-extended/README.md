# About Algolia's Scout-Extended

Algolia Scout-extended was created by, and is maintained by [Algolia](https://github.com/algolia).

## Get started locally
> requires :
* [Composer](https://getcomposer.org/)
* [PHP 7.1.3+](https://www.php.net/releases/index.php)

```bash
# Install from Algolia's Playground from Github
git clone https://github.com/algolia/api-clients-playground

# Move to Laravel-scout-extended folder.
cd api-clients-playground/laravel-scout-extended

# Install dependencies
composer install

# Add your credential in .env
ALGOLIA_APP_ID='YOUR APPLICATION ID'
ALGOLIA_SECRET='YOUR ADMIN API KEY'

# Your database already exists, so you just need to create data and send it to Algolia
php artisan db:seed && php artisan scout:import

# See the search query
php artisan serve
```

> See the result of the query here : [localhost](http://127.0.0.1:8000)
> Perform search queries with the `query` param : http://127.0.0.1:8000?query=foo




