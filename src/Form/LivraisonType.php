<?php

namespace App\Form;

use App\Entity\Livraison;
use App\Entity\Livreur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LivraisonType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('destination')
            ->add('date_liv')
            ->add('livreur',EntityType::class,[
                'class'=>Livreur::class,
                'multiple'=>false,
                'expanded'=>false

            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Livraison::class,
        ]);
    }
}
