<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass="App\Repository\ArticleRepository")
 */
class Article
{
    /**
     * @Groups({"searchable"})
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Groups({"searchable"})
     * @ORM\Column(type="string", length=36)
     */
    private $title;

    /**
     * @Groups({"searchable"})
     * @ORM\Column(type="string", length=36)
     */
    private $author;

    /**
     * @Groups({"searchable"})
     * @ORM\Column(type="integer", nullable=true)
     */
    private $likesCount;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

        return $this;
    }

    public function getAuthor(): ?string
    {
        return $this->author;
    }

    public function setAuthor(string $author): self
    {
        $this->author = $author;

        return $this;
    }

    public function getLikesCount(): ?int
    {
        return $this->likesCount;
    }

    public function setLikesCount(?int $likesCount): self
    {
        $this->likesCount = $likesCount;

        return $this;
    }
}
