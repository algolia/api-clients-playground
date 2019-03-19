# About Algolia's Scout-Extended

Algolia Scout-extended was created by and maintained by [Algolia](https://github.com/algolia). The Algolia Search API Client for Laravel. 

## Get started locally

>requires :
* [Composer](https://getcomposer.org/)
* [Laravel](https://laravel.com/)



First, follow Laravel's tutorial to install it. 

>[Laravel tutorial](https://laravel.com/docs/5.8/installation) 

Then: 

```bash
#Install from Algolia's Playground from Github
git clone https://github.com/algolia/api-clients-playground

# Move to Laravel-scout-extended folder.
cd api-clients-playground/laravel-scout-extended 

# Install dependencies
composer install

# Add your credential in .env
ALGOLIA_APP_ID='YOUR APPLICATION ID'
ALGOLIA_SECRET='YOUR ADMIN API KEY'

# Populate your Database
php artisan db:seed

#Populate your index
php artisan scout:import

# See the search query 
php artisan serve 

```
> See the result of the query here : [localhost](http://localhost:8000/)


 

