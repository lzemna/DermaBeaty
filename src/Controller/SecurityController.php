<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\InscriptionType;
use Doctrine\Persistence\ObjectManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoder;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class SecurityController extends AbstractController
{
    /**
     * @Route("/inscription", name="security_inscription")
     */
    public function inscription(Request $request,UserPasswordEncoderInterface $encoder): Response
    {
        $user = new User();
        $form = $this->createForm(InscriptionType::class,$user);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $manager=$this->getDoctrine()->getManager();
            $hash = $encoder->encodePassword($user,$user->getPassword());
           $user->setPassword($hash);
            $manager->persist($user);
            $manager->flush();
            $this->redirectToRoute('connection_login');


        }
        return $this->render('security/inscription.html.twig',['form'=>$form->createView()]);


    }

    /**
     * @Route ("/connection",name="connection_login")
     */
    public function login(){


    return $this->render('security/login.html.twig');
    }
    /**
     * @Route ("/logout",name="security_logout")
     */
    public function logout(){


    }

}
