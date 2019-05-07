<?php

namespace App\Controller;

use App\Entity\Article;
use Algolia\SearchBundle\IndexManagerInterface;
use Symfony\Component\HttpFoundation\Response;

class ArticleController
{

    protected $indexManager;

    public function __construct(IndexManagerInterface $indexingManager)
    {
        $this->indexManager = $indexingManager;
    }

    public function index()
    {
        $articles = $this->indexManager->rawSearch('', Article::class);
        var_dump($articles);
        $response = new Response();
        return $response;
    }
}