<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @UniqueEntity(
 * fields= {"email"},
 * message= "cet email est deja Utilise ! "
 * )
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
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    /**
     * @Assert\EqualTo(propertyPath="password",message=" vos mots de passes ne sont pas les memes")
     */
    public $confirm_password;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $username;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(min="8",minMessage="votre mot de passe doit faire minimum 8 caracteres")
     */
    private $password;

    /**
     * @ORM\OneToMany(targetEntity=Forum::class, mappedBy="user", orphanRemoval=true)
     */
    private $forums;

    /**
     * @ORM\OneToMany(targetEntity=Reply::class, mappedBy="User", orphanRemoval=true)
     */
    private $replies;

    /**
     * @ORM\OneToMany(targetEntity=ReplyLike::class, mappedBy="user", orphanRemoval=true)
     */
    private $replyLikes;

    public function __construct()
    {
        $this->forums = new ArrayCollection();
        $this->replies = new ArrayCollection();
        $this->replyLikes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }
    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }
    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }
    public function getRoles()
    {
       return ['ROLE_USER'];
        // TODO: Implement getRoles() method.
    }

    /**
     * @return Collection|Forum[]
     */
    public function getForums(): Collection
    {
        return $this->forums;
    }

    public function addForum(Forum $forum): self
    {
        if (!$this->forums->contains($forum)) {
            $this->forums[] = $forum;
            $forum->setUser($this);
        }

        return $this;
    }

    public function removeForum(Forum $forum): self
    {
        if ($this->forums->removeElement($forum)) {
            // set the owning side to null (unless already changed)
            if ($forum->getUser() === $this) {
                $forum->setUser(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Reply[]
     */
    public function getReplies(): Collection
    {
        return $this->replies;
    }

    public function addReply(Reply $reply): self
    {
        if (!$this->replies->contains($reply)) {
            $this->replies[] = $reply;
            $reply->setUser($this);
        }

        return $this;
    }

    public function removeReply(Reply $reply): self
    {
        if ($this->replies->removeElement($reply)) {
            // set the owning side to null (unless already changed)
            if ($reply->getUser() === $this) {
                $reply->setUser(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|ReplyLike[]
     */
    public function getReplyLikes(): Collection
    {
        return $this->replyLikes;
    }

    public function addReplyLike(ReplyLike $replyLike): self
    {
        if (!$this->replyLikes->contains($replyLike)) {
            $this->replyLikes[] = $replyLike;
            $replyLike->setUser($this);
        }

        return $this;
    }

    public function removeReplyLike(ReplyLike $replyLike): self
    {
        if ($this->replyLikes->removeElement($replyLike)) {
            // set the owning side to null (unless already changed)
            if ($replyLike->getUser() === $this) {
                $replyLike->setUser(null);
            }
        }

        return $this;
    }

}
