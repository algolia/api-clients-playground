<?php

namespace App\DataFixtures;

use App\Entity\Article;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;

class AppFixtures extends Fixture
{
    public function load(ObjectManager $manager)
    {
        for ($i = 0; $i < 20; $i++) {
            $article = new Article();
            $article->setTitle('Foo');
            $article->setAuthor('Bar');
            $article->setLikesCount(mt_rand(10, 100));
            $manager->persist($article);
        }

        $manager->flush();
    }
}
