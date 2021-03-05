<?php

namespace App\Form;

use App\Entity\Formulaire;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class FormulaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('ref')
            ->add('cin')
            ->add('quest1')
            ->add('quest2')
            ->add('quest3')
            ->add('quest4')
            ->add('quest5')
            ->add('quest6')
            ->add('type')
            ->add('FormCateg');


        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Formulaire::class,
        ]);
    }
}
