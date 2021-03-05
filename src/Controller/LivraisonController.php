<?php

namespace App\Controller;

use App\Entity\Livraison;
use App\Entity\Livreur;
use App\Form\LivraisonType;
use App\Form\LivreurType;
use App\Repository\LivraisonRepository;
use App\Repository\LivreurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LivraisonController extends AbstractController
{
    /**
     * @Route("/livraison", name="livraison")
     */
    public function index(LivraisonRepository $repository): Response
    {
        return $this->render('livraison/index.html.twig', [
            'livraison' => $repository->findAll()
        ]);
    }
    /**
     * @Route("/livraison/add", name="livraison_add")
     */
    public function addLivraison(Request $request): Response
    {
        $livraison = new Livraison();
        $form = $this->createForm(LivraisonType::class,$livraison);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
        $manager = $this->getDoctrine()->getManager();
        $manager->persist($livraison);
        $manager->flush();


            return $this->redirectToRoute('livraison');
        }
        return $this->render('livraison/addLivraison.html.twig',['form'=>$form->createView(),'action'=>'ajouter']);
    }
    /**
     * @Route("/livraison/rem/{id}", name="livraison_rem")
     */
    public function removeLivraison($id, LivraisonRepository $repository): Response
    {
        $livraison = $repository->find($id);
        $manager = $this->getDoctrine()->getManager();
        $manager->remove($livraison);
        $manager->flush();

        return $this->redirectToRoute('livraison');
    }
    /**
     * @Route("/livraison/edit/{id}", name="livraison_edit")
     */
    public function editLivraison($id, LivraisonRepository $repository,Request $request): Response
    {
        $livraison = $repository->find($id);
        $form = $this->createForm(LivraisonType::class,$livraison);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $manager = $this->getDoctrine()->getManager();
            $manager->persist($livraison);
            $manager->flush();


            return $this->redirectToRoute('livraison');
        }
        return $this->render('livraison/addLivraison.html.twig',['form'=>$form->createView(),'action'=>'modifier']);
    }
    //livreur
    /**
     * @Route("/livreur", name="livreur")
     */
    public function afficheLivreur(LivreurRepository $repository): Response
    {
        return $this->render('livraison/livreur.html.twig', [
            'livreur' => $repository->findAll()
        ]);
    }
    /**
     * @Route("/livreur/add", name="livreur_add")
     */
    public function addLivreur(Request $request): Response
    {
        $livreur = new Livreur();
        $form = $this->createForm(LivreurType::class,$livreur);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $manager = $this->getDoctrine()->getManager();
            $manager->persist($livreur);
            $manager->flush();


            return $this->redirectToRoute('livreur');
        }
        return $this->render('livraison/addLivreur.html.twig',['form'=>$form->createView(),'action'=>'ajouter']);
    }
    /**
     * @Route("/livreur/rem/{id}", name="livreur_rem")
     */
    public function removeLivreur($id, LivreurRepository $repository): Response
    {
        $livraison = $repository->find($id);
        $manager = $this->getDoctrine()->getManager();
        $manager->remove($livraison);
        $manager->flush();

        return $this->redirectToRoute('livreur');
    }
    /**
     * @Route("/livreur/redit/{id}", name="livreur_edit")
     */
    public function editLivreur($id,Request $request,LivreurRepository $repository): Response
    {
        $livreur = $repository->find($id);
        $form = $this->createForm(LivreurType::class,$livreur);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $manager = $this->getDoctrine()->getManager();
            $manager->persist($livreur);
            $manager->flush();


            return $this->redirectToRoute('livreur');
        }
        return $this->render('livraison/addLivreur.html.twig',['form'=>$form->createView(),'action'=>'modifier']);
    }
}
