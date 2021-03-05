<?php

namespace App\Entity;

use App\Repository\ConseilRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ConseilRepository::class)
 */
class Conseil
{


    /**
     * @ORM\Id
     * @Assert\Positive(message="ce champs doit etre positive")
     * @Assert\NotBlank(message="obligatoire")
     * @ORM\Column(type="integer")
     *
     */
    private $reference;

    /**
     *
     * @Assert\NotBlank(message="le nom est obligatoire")
     * @Assert\Regex(pattern="/^[a-z]+$/i", message="ce champs n'accepte pas les chiffres")
     * @ORM\Column(type="string", length=255)
     */
    private $remarques;

    /**
     *
     * @ORM\Column(type="date")
     */
    private $date_red;

    /**
     * @ORM\Column(type="date")
     */
    private $date_limite;

    /**
     * @Assert\NotBlank(message="le nom est obligatoire")
     * @Assert\Regex(pattern="/^[a-z]+$/i", message="ce champs n'accepte pas les chiffres")
     * @ORM\Column(type="string", length=255)
     */
    private $nom_derma;

    /**
     * @Assert\Email(message="saisir votre email correctement")
     * @Assert\NotBlank(message="le nom est obligatoire")
     * @Assert\Regex(pattern="/^[a-z0-9]+$/i", message="ce champs accepte un format d'email")
     * @ORM\Column(type="string", length=255)
     */
    private $email;



    public function getReference(): ?int
    {
        return $this->reference;
    }
    public function setReference(int $reference): self
    {
       $this->reference= $reference;
       return $this;
    }


    public function getRemarques(): ?string
    {
        return $this->remarques;
    }

    public function setRemarques(string $remarques): self
    {
        $this->remarques = $remarques;

        return $this;
    }

    public function getDateRed(): ?\DateTimeInterface
    {
        return $this->date_red;
    }

    public function setDateRed(\DateTimeInterface $date_red): self
    {
        $this->date_red = $date_red;

        return $this;
    }

    public function getDateLimite(): ?\DateTimeInterface
    {
        return $this->date_limite;
    }

    public function setDateLimite(\DateTimeInterface $date_limite): self
    {
        $this->date_limite = $date_limite;

        return $this;
    }

    public function getNomDerma(): ?string
    {
        return $this->nom_derma;
    }

    public function setNomDerma(string $nom_derma): self
    {
        $this->nom_derma = $nom_derma;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }
}
