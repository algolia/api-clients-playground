# About Algolia's Symfony bundle

Algolia Symfony bundle was created by, and is maintained by [Algolia](https://github.com/algolia).

## Get started locally
> requires :
* [Composer](https://getcomposer.org/)
* [PHP 7.1.3+](https://www.php.net/releases/index.php)

```bash
# Install from Algolia's Playground from Github
git clone https://github.com/algolia/api-clients-playground

# Move to Symfony folder.
cd api-clients-playground/symfony

# Install dependencies
composer install

# Configure your database drive for sqlite in .env
DATABASE_URL=sqlite:///%kernel.project_dir%/var/data.db

# Generate migration file
php bin/console make:migration

# Migrate you database in you local storage
php bin/console doctrine:migrations:migrate

# Add your credential in .env
ALGOLIA_APP_ID='YOUR APPLICATION ID'
ALGOLIA_SECRET='YOUR ADMIN API KEY'

# Fill you local database
php bin/console doctrine:fixtures:load

# Import your data to algolia
php bin/console search:import    

# See the search query
php bin/console server:run
```

> See the result of the query here : [localhost](http://127.0.0.1:8000)
