<?php

namespace App\Controller;

use App\Entity\Conseil;
use App\Form\ConseilType;
use App\Repository\ConseilRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
class ConseilController extends AbstractController
{
    /**
     * @Route("/conseil", name="conseil")
     */
    public function index(): Response
    {
        return $this->render('conseil/index.html.twig', [
            'controller_name' => 'ConseilController',
        ]);
    }

    /**
     * @Route("/affichO", name="affichO")
     */
    public function list()
    {
        $conseils = $this->getDoctrine()->getRepository(Conseil::class)->findAll();

        return $this->render('Conseil/list.html.twig', [
            "conseils" => $conseils,]);

    }
    /**
     * @Route("/affichOf", name="affichOf")
     */
    public function listf()
    {
        $conseils = $this->getDoctrine()->getRepository(Conseil::class)->findAll();

        return $this->render('Conseil/list_front.html.twig', [
            "conseils" => $conseils,]);

    }
    /**
     * @Route("/conseil/supp/{reference}", name="supprimerO")
     */
    public function supprimer($reference)
    {
        $conseil = $this->getDoctrine()->getRepository(Conseil::class)->find($reference);
        $em = $this->getDoctrine()->getManager();
        $em->remove($conseil);
        $em->flush();
        return $this->redirectToRoute('affichO');
    }


    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("conseil/ajout", name="ajoutO")
     */
    function ajout(Request $request)
    {
        $conseil = new Conseil();
        $form = $this->createForm( ConseilType::class, $conseil);
        $form->add('ajouter', SubmitType::class);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($conseil);

            $em->flush();
            return $this->redirectToRoute('affichO');
        }
        return $this->render('conseil/ajout.html.twig', [
            'form' => $form->createView()
        ]);
    }

    /**
     * @route ("conseil/modifier/{reference}", name="modifierO")
     */
    function modifier(ConseilRepository $repository, Request $request, $reference)
    {
        $conseils = $repository->find($reference);
        $form = $this->createForm(ConseilType::class, $conseils);
        $form->add('modifier', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('affichO');
        }
        return $this->render('conseil/modifier.html.twig', [
            'f' => $form->createView()
        ]);
    }
}
