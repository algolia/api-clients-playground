# About Algolia's Scout-Extended

Algolia Scout-extended was created bt and mainteained by [Algolia](https://github.com/algolia). The Algolia Search API Client for Laravel. 

## Get started locally

>requires :
* [Composer](https://getcomposer.org/)
* [Laravel](https://laravel.com/)



First, follow Laravel's tutorial to install it. 

>[Laravel tutorial](https://laravel.com/docs/5.8/installation) 

Then: 

```
# Install from Algolia's Playground from Github
git clone https://github.com/algolia/api-clients-playground

# Move to Laravel-scout-extended folder.
cd laravel-scout-extended 

# Install dependencies
composer install

# Copy .env file as .env.example
cp .env.example .env

# Add your credential in .env
ALGOLIA_APP_ID='YOUR APPLICATION ID'
ALGOLIA_SECRET='YOUR ADMIN API KEY'

# Move to database directory and create database.sqlite
cd database && touch database.sqlite

# Go back and migrate database
php artisan migrate 

# Populated it.
php artisan db:seed

# Add all objects to you index using scout-extend 
php artisan scout:import

# See the search query 

php aristan serve 

```
> See the result of the query here : [localhost](http://localhost:8000/)

*if you want to send them to a specific index*

Add this method to **App/Article.php**
```
public function searchableAs()
{
    return 'Your Index';
}
```

Feel free to play ! 

Here is the documentation to have all the possibility you want for scout-extended [Scout-Extended](https://www.algolia.com/doc/framework-integration/laravel/getting-started/introduction-to-scout-extended/)

Enjoy ! 
 

