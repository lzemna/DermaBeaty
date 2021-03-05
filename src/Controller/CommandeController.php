<?php

namespace App\Controller;

use App\Entity\Commande;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use App\Form\CommandeType;
use App\Repository\CommandeRepository;
use App\Repository\ProduitRepository;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    /**
     * @Route("/afficherC", name="afficher_C")
     */

    public function afficher()
    {
        $commandes=$this->getDoctrine()->getRepository(Commande::class)->findAll();
        return $this->render('commande/afficher.html.twig', [
            'commandes' => $commandes,]);
    }
    /**
     * @Route("/supprimerC/{id}", name="supprimer_C")
     */
    public function supprimer($id)
    {
        $commandes=$this->getDoctrine()->getRepository(Commande::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($commandes);
        $em->flush();
        return $this->redirectToRoute('afficher_C');
    }
    /**
     * @Route("ajouterC", name="ajouter_C")
     */
    function ajouter(Request $request ) {
        $commandes=new Commande();
        $form=$this->createForm(CommandeType::class,$commandes);
        $form->add('Ajouter',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->persist($commandes);
            $em->flush();
            return $this->redirectToRoute('afficher_C');
        }
        return $this->render('commande/ajouter.html.twig',['formajoutc'=>$form->createView()]);
    }
    /**
     * @Route("modifierC/{id}",name="modifier_C")
     */
    public function Modifier($id,Request $request)
    {
        $commandes=$this->getDoctrine()->getRepository(Commande::class)->find($id);
        $form=$this->createForm(CommandeType::class,$commandes);
        $form->add('Modifier',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->flush();
            return $this->redirectToRoute('afficher_C');
        }
        return $this->render('commande/modifier.html.twig',['formmodificationc'=>$form->createView()]);
    }
}
