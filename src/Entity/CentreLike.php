<?php

namespace App\Entity;

use App\Repository\CentreLikeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CentreLikeRepository::class)
 */
class CentreLike
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="yes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Centre::class, inversedBy="yes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $centre;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getCentre(): ?Centre
    {
        return $this->centre;
    }

    public function setCentre(?Centre $centre): self
    {
        $this->centre = $centre;

        return $this;
    }
}
