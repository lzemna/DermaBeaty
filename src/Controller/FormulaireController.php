<?php

namespace App\Controller;

use App\Entity\FormCateg;
use App\Entity\Formulaire;
use App\Form\FormulaireType;
use App\Repository\FormulaireRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Util\StringUtil;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
class FormulaireController extends AbstractController
{
    /**
     * @Route("/affichF", name="affichF")
     */
    public function list()
    {
        $forms = $this->getDoctrine()->getRepository(Formulaire::class)->findAll();

        return $this->render('formulaire/list.html.twig', [
            "forms" => $forms,]);

    }
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("formulaire_front/ajout/{idcat}", name="ajoutFf")
     */
    function ajoutF (Request $request,$idcat)
    {   /* $cat->setIdCat($idcat);
        $form1->setFormCateg($cat);*/
        $form1 = new Formulaire();
        $form = $this->createForm( FormulaireType::class, $form1);
        $form->add('ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($form1);
            $em->flush();
            return $this->redirectToRoute('affichFCf');
        }
        return $this->render('formulaire/ajout_front.html.twig', [
            'formf' => $form->createView(),
            'idcat' => $idcat
        ]);
    }
    /**
     * @Route("/formulaire/supp/{ref}", name="supprimerF")
     */
    public function supprimer($ref)
    {
        $forms = $this->getDoctrine()->getRepository(Formulaire::class)->find($ref);
        $em = $this->getDoctrine()->getManager();
        $em->remove($forms);
        $em->flush();
        return $this->redirectToRoute('affichF');
    }


    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("formulaire/ajout", name="ajoutF")
     */
    function ajout(Request $request)
    {
        $form1 = new Formulaire();
        $form = $this->createForm( FormulaireType::class, $form1);
        $form->add('ajouter', SubmitType::class);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($form1);

            $em->flush();
            return $this->redirectToRoute('affichF');
        }
        return $this->render('formulaire/ajout.html.twig', [
            'form' => $form->createView()
        ]);
    }

    /**
     * @route ("formulaire/modifier/{ref}", name="modifierF")
     */
    function modifier(FormulaireRepository $repository, Request $request, $ref)
    {
        $form1 = $repository->find($ref);
        $form = $this->createForm(FormulaireType::class, $form1);
        $form->add('modifier', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('affichF');
        }
        return $this->render('formulaire/modifier.html.twig', [
            'f' => $form->createView()
        ]);
    }
}
