<?php

require __DIR__.'/../vendor/autoload.php';

(new \NunoMaduro\Collision\Provider())->register();

// Here goes your code:
$client = \Algolia\AlgoliaSearch\SearchClient::create('..', '..');
$index = $client->initIndex('contacts');
