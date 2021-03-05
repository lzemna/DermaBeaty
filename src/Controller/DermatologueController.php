<?php

namespace App\Controller;

use App\Entity\CategorieDerm;
use App\Entity\Dermatologue;
use App\Form\DermatologueType;
use App\Repository\DermatologueRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DermatologueController extends AbstractController
{
    /**
     * @Route("/dermatologues", name="dermatologues")
     */
    public function list ()
    {

        $dermatologues = $this->getDoctrine()->getRepository(Dermatologue::class)->findAll();


        return $this->render('dermatologue/affichederm.html.twig', [
            'dermatologues' => $dermatologues,
        ]);
    }

   /**
     * @Route("/dermatologuesfront/affiche/{ref}", name="dermfront")
     */
    public function listfront ($ref)
    {

        $dermatologues = $this->getDoctrine()->getRepository(Dermatologue::class)->findAll();
        $categories = $this->getDoctrine()->getRepository(CategorieDerm::class)->findAll();

        return $this->render('dermatologue/afficherdermatologuefront.html.twig', [
            'dermatologues' => $dermatologues,
            'ref'=> $ref,
            'categories'=> $categories
        ]);
    }

    /**
     * @Route("/dermatologuesfront/affiche1/{cin}", name="dermfront1")
     */
    public function listfrontderm ($cin)
    {

        $dermatologues = $this->getDoctrine()->getRepository(Dermatologue::class)->find($cin);


        return $this->render('dermatologue/afficher1dermatologuefront.html.twig', [
            'dermatologues' => $dermatologues,
            'cin'=> $cin
        ]);
    }




    /**
     * @Route("/Supderm/{cin}", name="derm")
     */
    public function Delete($cin ,DermatologueRepository $repository)
    {
        $dermatologue=$repository->find($cin);
        $em=$this->getDoctrine()->getManager();
        $em->remove($dermatologue);
        $em->flush();

        return $this->redirectToRoute('dermatologues');

    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route ("dermatologue/Ajoutderm" ,  name="ajoutderm")
     */
    function add(Request $request ) {


        $dermatologues=new Dermatologue();
        $form=$this->createForm(DermatologueType::class,$dermatologues);
        $form->add('Ajouter', SubmitType::class) ;
        $form->handleRequest($request);
        //$dc2array = unserialize($dermatologues->getAssurancemaladie(), ['allowed_classes' => false]);
        //var_dump($dc2array);

        if($form->isSubmitted()&& $form->isValid())
        {
            $path=$this->getParameter('kernel.project_dir').'/public/back/img';
            $dermatologues=$form->getData();
            $file=$dermatologues->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move($path,$fileName);
            }catch(FileException $e){

            }
            $em=$this->getDoctrine()->getManager() ;
            $dermatologues->setImage($fileName);
            $em->persist($dermatologues);
            $em->flush();
            return $this->redirectToRoute('dermatologues');
        }
        return $this->render ('dermatologue/Ajoutderm.html.twig',[
            'form'=>$form->createView()
        ]) ;
    }


    /**
     * @Route ("dermatologue/Update/{cin}" , name="updatederm")
     */

    function Update(DermatologueRepository $repository,$cin,Request $request){
        $dermatologues=$repository->find($cin);
        $form=$this->createForm(DermatologueType::class,$dermatologues) ;
        $form->add('Modifier',SubmitType::class) ;
        $form->handleRequest($request) ;
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->flush();
            return $this->redirectToRoute('dermatologues');
        }
        return $this->render('dermatologue/ModifDerm.html.twig',[
            'f'=>$form->createView()
        ]) ;
    }
}
