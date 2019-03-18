<?php

use Faker\Generator as Faker;
use App\Article;
$factory->define(Article::class, function (Faker $faker) {
    return [
        'image_url' => $faker->imageUrl(),
        'title' => $faker->realText($faker->numberBetween(10, 20)),
        'author' => $faker->name,
        'likes_count' => $faker->randomNumber(3),
        'slug' => $faker->slug,
    ];
});
