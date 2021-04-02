<?php

namespace App\Entity;

use App\Repository\LivraisonRepository;
use Doctrine\ORM\Mapping as ORM;
use PhpParser\Node\Scalar\String_;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=LivraisonRepository::class)
 */
class Livraison
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="champs obligatoire")
     */
    private $destination;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="champs obligatoire")
     */
    private $date_liv;

    /**
     * @ORM\ManyToOne(targetEntity=Livreur::class, inversedBy="livraisons")
     */
    private $livreur;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDestination(): ?string
    {
        return $this->destination;
    }

    public function setDestination(string $destination): self
    {
        $this->destination = $destination;

        return $this;
    }

    public function getDateLiv(): ?\DateTimeInterface
    {
        return $this->date_liv;
    }

    public function setDateLiv(\DateTimeInterface $date_liv): self
    {
        $this->date_liv = $date_liv;

        return $this;
    }

    public function getLivreur(): ?Livreur
    {
        return $this->livreur;
    }


    public function setLivreur(?Livreur $livreur): self
    {
        $this->livreur = $livreur;

        return $this;
    }



}
