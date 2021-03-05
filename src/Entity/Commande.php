<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Champs obligatoir")
     */
    private $date;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="Champs obligatoir")
     */
    private $prixtotal;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="Commande")
     * @Assert\NotBlank(message="Champs obligatoir")
     */
    private $produits;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getPrixtotal(): ?float
    {
        return $this->prixtotal;
    }

    public function setPrixtotal(float $prixtotal): self
    {
        $this->prixtotal = $prixtotal;

        return $this;
    }

    /**
     * @return Collection|Produit[]
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits[] = $produit;
            $produit->setCommande($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getCommande() === $this) {
                $produit->setCommande(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return (string)$this->getidc();

    }
}
