<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{

    /**
     * @ORM\Id
     * @ORM\Column(type="string", length=255)
     */
    private $Reference_P;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Nom_P;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Type_P;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Marque_P;

    /**
     * @ORM\Column(type="float")
     *  @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Prix_P;

    /**
     * @ORM\Column(type="integer", nullable=true)
     *  @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Quantite_P;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez ajouter image")
     * @Assert\File(mimeTypes={"image/jpeg"})
     */
    private $Image_P;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="Champs obligatoir")
     */
    private $Description_P;

    /**
     * @ORM\ManyToOne(targetEntity=CategorieP::class, inversedBy="produits")
     * @ORM\JoinColumn(nullable=false,name="Categorie", referencedColumnName="reference_c")
     */
    private $Categorie;

    /**
     * @ORM\ManyToOne(targetEntity=Commande::class, inversedBy="produits")
     */
    private $Commande;



    public function getReferenceP(): ?string
    {
        return $this->Reference_P;
    }

    public function setReferenceP(string $Reference_P): self
    {
        $this->Reference_P = $Reference_P;

        return $this;
    }

    public function getNomP(): ?string
    {
        return $this->Nom_P;
    }

    public function setNomP(string $Nom_P): self
    {
        $this->Nom_P = $Nom_P;

        return $this;
    }

    public function getTypeP(): ?string
    {
        return $this->Type_P;
    }

    public function setTypeP(string $Type_P): self
    {
        $this->Type_P = $Type_P;

        return $this;
    }

    public function getMarqueP(): ?string
    {
        return $this->Marque_P;
    }

    public function setMarqueP(string $Marque_P): self
    {
        $this->Marque_P = $Marque_P;

        return $this;
    }

    public function getPrixP(): ?float
    {
        return $this->Prix_P;
    }

    public function setPrixP(float $Prix_P): self
    {
        $this->Prix_P = $Prix_P;

        return $this;
    }

    public function getQuantiteP(): ?int
    {
        return $this->Quantite_P;
    }

    public function setQuantiteP(?int $Quantite_P): self
    {
        $this->Quantite_P = $Quantite_P;

        return $this;
    }

    public function getImageP()
    {
        return $this->Image_P;
    }

    public function setImageP( $Image_P)
    {
        $this->Image_P = $Image_P;

        return $this;
    }

    public function getDescriptionP(): ?string
    {
        return $this->Description_P;
    }

    public function setDescriptionP(string $Description_P): self
    {
        $this->Description_P = $Description_P;

        return $this;
    }

    public function getCategorieP(): ?CategorieP
    {
        return $this->CategorieP;
    }

    public function setCategorieP(?CategorieP $CategorieP): self
    {
        $this->CategorieP = $CategorieP;

        return $this;
    }

    public function getCategorie(): ?CategorieP
    {
        return $this->Categorie;
    }

    public function setCategorie(?CategorieP $Categorie): self
    {
        $this->Categorie = $Categorie;

        return $this;
    }

    public function getCommande(): ?Commande
    {
        return $this->Commande;
    }

    public function setCommande(?Commande $Commande): self
    {
        $this->Commande = $Commande;

        return $this;
    }


    public function __toString()
    {
        return (string)$this->getNomP();

    }
}
