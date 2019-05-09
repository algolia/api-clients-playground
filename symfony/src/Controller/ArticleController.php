<?php

namespace App\Controller;

use App\Entity\Article;
use Algolia\SearchBundle\IndexManagerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;

class ArticleController
{
    private $indexManager;

    public function __construct(IndexManagerInterface $indexingManager)
    {
        $this->indexManager = $indexingManager;
    }

    public function index()
    {
        $articles = $this->indexManager->rawSearch('', Article::class);

        return new JsonResponse($articles);
    }
}
