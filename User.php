<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")
 * @UniqueEntity(fields={"email"}, message="Un utilisateur existe déjà avec cette adresse email.")
 */
class User implements UserInterface
{

    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Assert\Length(min=8, minMessage="entrer un nombre de taille egale a 8")
     * @Assert\Positive(message="ce champs doit etre positive")
     * @Assert\NotBlank(message="obligatoire")
     * @ORM\Column(type="integer")
     */
    private $cin;

    /**
     * @Assert\NotBlank(message="obligatoire")
     * @Assert\Email(message="saisir votre email correctement")
     * @ORM\Column(type="string", length=180, unique=true)
     */
    private $email;

    /**
     *
     * @ORM\Column(type="json")
     */
    private $roles = [];

    /**
     *
     * @var string The hashed password
     * @ORM\Column(type="string")
     */
    private $password;



    /**
     *@Assert\NotBlank(message="obligatoire")
     * @Assert\Regex(pattern="/^[a-z]+$/i", message="ce champs n'accepte pas les chiffres")
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    /**
     * @Assert\NotBlank(message="obligatoire")
     * @Assert\Regex(pattern="/^[a-z]+$/i", message="ce champs n'accepte pas les chiffres")
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     *  @Assert\NotBlank(message="obligatoire")
     *  @Assert\Regex(pattern="/^[a-z0-9]+$/i", message="ce champs accepte un format d'adresse")
     * @ORM\Column(type="string", length=255)
     */
    private $adresse;

    /**
     *
     * @Assert\NotBlank(message="obligatoire")
     * @Assert\Positive(message="ce champs doit etre positive")
     * @Assert\Length(min=8, minMessage="entrer un nombre de taille egale a 8")
     * @ORM\Column(type="integer")
     */
    private $numero;



    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUsername(): string
    {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function getPassword(): string
    {
        return (string) $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }
    public function getId(): ?int
    {
        return $this->id;
    }
    public function getCin(): ?int
    {
        return $this->cin;
    }
    public function setCin(string $cin): self
    {
        $this->cin = $cin;

        return $this;
    }



    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNumero(): ?int
    {
        return $this->numero;
    }

    public function setNumero(int $numero): self
    {
        $this->numero = $numero;

        return $this;
    }
    public function addRoles(string $roles): self
    {
        if (!in_array($roles, $this->roles)) {
            $this->roles[] = $roles;
        }

        return $this;
    }
}
