<?php

namespace App\DataFixtures;

use App\Entity\Admin;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Faker\Factory;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class AppFixtures extends Fixture
{
    private $hash;

    public function __construct(UserPasswordEncoderInterface $encoder)
    {
        $this->hash = $encoder;

    }

    public function load(ObjectManager $manager)
    {
        $faker = Factory::create('fr_FR');
        for ($i = 0; $i < 5; $i++) {
            $user = new Admin();
            $user->setNom($faker->nom);
            $user->setPrenom($faker->prenom);
            $user->setAdresse($faker->adresse);
            $user->setNumero($faker->numberBetween(100, 500));
            $user->setEmail($faker->email);
            $user->setRole("admin");
            $encoder = $this->hash;
            $user->setMdp($encoder->encodePassword($user, '123456789'));

            $manager->persist($user);

            $manager->flush();
        }
    }
}
