<?php


namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Algolia\SearchBundle\Entity\Aggregator;

/**
 * @ORM\Entity
 */
class News extends Aggregator
{
    /**
     * Returns the entities class names that should be aggregated.
     *
     * @return string[]
     */
    public static function getEntities()
    {
        return [
            Post::class,
            Article::class,
        ];
    }
}
