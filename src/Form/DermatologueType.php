<?php

namespace App\Form;

use App\Entity\Dermatologue;
use Doctrine\DBAL\Types\DateType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\LanguageType;
use Symfony\Component\Form\Extension\Core\Type\RadioType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\CallbackTransformer;
class DermatologueType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('cin')
            ->add('diplome')
            ->add('formation')
            ->add('langue',LanguageType::class,['required' => false])
            ->add('horaire',ChoiceType::class,[
                'choices'=>[
                    'du 09:00->18h:00'=>'du 09:00->18h:00',
                    'du 09:00->14h30'=>'du 09:00->14h30',
                    'du 09:00->14h00'=>'du 09:00->14h00',
                    'Pas Disponible'=>'Pas Disponible',
                    ]
                ,'group_by' => function($choice, $key, $value) {
        if (($choice == "du 09:00->18h:00") || ($choice =="du 09:00->14h30")) {
            return 'du lundi au vendredi ';
        }
        else
        return 'Samedi';
    },'required' => false,

            ])
            ->add('modereglement',ChoiceType::class,[
                'choices'=>[
                    'Cheque'=>'cheque',
                    'Espèce'=>'epece',
                    'Carte Bancaire'=>'carte bancaire',
                ]
                ,'expanded'=>true

            ])
            ->add('assurancemaladie',ChoiceType::class,[
                'choices'=>[
                    'CNAM'=>"CNAM",
                    'Biat Assurrance'=>"Biat Assurrance",
                    'Gat Assurance'=>"Gat Assurance" ,
                    'Comar Assurance'=>"Comar Assurance"
                ],'required' => false,

            ])
            ->add('image',FileType::class, array('data_class' => null))
            ->add('categorie')
        ;

       /* $builder->get('modereglement')
            ->addModelTransformer(new CallbackTransformer(
                function ($modereglementArray) {
                    // transform the array to a string
                    return count($modereglementArray)? $modereglementArray[0]: null;
                },

                function ($modereglementString) {
                    // transform the string back to an array
                    return [$modereglementString];
                }));
      */
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Dermatologue::class,
        ]);
    }
}
