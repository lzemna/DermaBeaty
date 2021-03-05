<?php

namespace App\Controller;

use App\Entity\CategorieP;
use App\Entity\Produit;
use App\Repository\CategoriePRepository;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\CategoriePType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;

class CategoriePController extends AbstractController
{
    /**
     * @Route("/afficherCP", name="afficher_CP")
     */

    public function afficher()
    {
        $categories=$this->getDoctrine()->getRepository(CategorieP::class)->findAll();
        return $this->render('categorie_p/afficher.html.twig', [
            'categories' => $categories,]);
    }



    /**
     * @Route("/afficherCPF/{id}", name="afficher_CPF")
     */

    public function afficherf($id,CategoriePRepository $repository,ProduitRepository $produitRepository)
    {
        $cath = $repository->find($id);
        $prod = $produitRepository->findBycath($id);

        $cathegorie = $repository->findAll();

        $categories=$this->getDoctrine()->getRepository(CategorieP::class)->findAll();
        return $this->render('produit/afficherfront.html.twig', [
            'categories' => $cathegorie,'produits'=>$prod,'cath'=>$cath]);
    }
    /**
     * @Route("/supprimerCP/{Reference_C}", name="supprimer_CP")
     */
    public function supprimer($Reference_C)
    {
        $categories=$this->getDoctrine()->getRepository(CategorieP::class)->find($Reference_C);
        $em=$this->getDoctrine()->getManager();
        $em->remove($categories);
        $em->flush();
        return $this->redirectToRoute('afficher_CP');
    }
    /**
     * @Route("ajouterCP", name="ajouter_CP")
     */
    function ajouter(Request $request ) {
        $categories=new CategorieP();
        $form=$this->createForm(CategoriePType::class,$categories);
        $form->add('Ajouter',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->persist($categories);
            $em->flush();
            return $this->redirectToRoute('afficher_CP');
        }
        return $this->render('categorie_p/ajouter.html.twig',['formajoutC'=>$form->createView()]);
    }
    /**
     * @Route("modifierCP/{Reference_C}",name="modifier_CP")
     */
    public function Modifier($Reference_C,Request $request)
    {
        $categories=$this->getDoctrine()->getRepository(CategorieP::class)->find($Reference_C);
        $form=$this->createForm(CategoriePType::class,$categories);
        $form->add('Modifier',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->flush();
            return $this->redirectToRoute('afficher_CP');
        }
        return $this->render('categorie_p/modifier.html.twig',['formmodificationC'=>$form->createView()]);
    }
}
