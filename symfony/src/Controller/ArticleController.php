<?php

namespace App\Controller;

use Algolia\SearchBundle\SearchService;
use App\Entity\Article;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;

class ArticleController extends AbstractController
{
    private $searchService;

    public function __construct(SearchService $searchService)
    {
        $this->searchService = $searchService;
    }

    /**
     * @return JsonResponse
     */
    public function index(): JsonResponse
    {
        $result = $this->searchService->rawSearch(Article::class, '');

        return new JsonResponse($result);
    }
}
