<?php

namespace App\Controller;
use App\Entity\Produit;
use App\Repository\CategoriePRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\ProduitType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Repository\ProduitRepository;





class ProduitController extends AbstractController
{ /**
 * @Route("/afficherPF/{Reference_C}", name="afficher_PF")
 */

    public function afficherfront($Reference_C)
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/afficherfront.html.twig', [

            'produits' => $produits,
            'ReferenceC'=>$Reference_C]);
    }
    /**
     * @Route("/afficherPF", name="afficher_TPF")
     */

    public function afficherAvecCath(CategoriePRepository $repository)
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/afficherfront.html.twig', [
            'produits' => $produits
            ,'categories'=>$repository->findAll(),'cath'=>'all']);
    }


    /**
     * @Route("/afficherP", name="afficher_P")
     */

    public function afficher()
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/afficher.html.twig', [
            'produits' => $produits,]);
    }

    /**
     * @Route("/supprimerP/{Reference_P}", name="supprimer_P")
     */
    public function supprimer($Reference_P)
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->find($Reference_P);
        $em=$this->getDoctrine()->getManager();
        $em->remove($produits);
        $em->flush();
        return $this->redirectToRoute('afficher_P');
    }
    /**
     * @Route("ajouterP", name="ajouter_P")
     */
    function ajouter(Request $request ) {
        $produits=new Produit();
        $form=$this->createForm(ProduitType::class,$produits);
        $form->add('Ajouter',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $file=$produits->getImageP();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('kernel.project_dir'). '/public/back/img',
                    $fileName
                );
            } catch(FileException $e){
                // ...handle exception
            }
            $em=$this->getDoctrine()->getManager() ;
            $produits->setImageP($fileName);
            $em->persist($produits);
            $em->flush();
            return $this->redirectToRoute('afficher_P');
        }
        return $this->render('produit/ajouter.html.twig',['formajoutP'=>$form->createView()]);
    }
    /**
     * @Route("modifierP/{Reference_P}",name="modifier_P")
     */
    public function Modifier($Reference_P,Request $request)
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->find($Reference_P);
        $form=$this->createForm(ProduitType::class,$produits);
        $form->add('Modifier',SubmitType::class,['attr'=>['formnovalidate'=>'formnovalidate']]);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager() ;
            $em->flush();
            return $this->redirectToRoute('afficher_P');
        }
        return $this->render('produit/modifier.html.twig',['formmodificationP'=>$form->createView()]);
    }
//panier
    /**
     * @Route("/product", name="product")
     */
    public function index(ProduitRepository $repository,SessionInterface $session): Response
    {
        $panier = $session->get('panier',[]);
        $nb= 0;
        foreach ($panier as $id=>$quantite){
            $nb+=$quantite;

        }






        return $this->render('product/index.html.twig', [
            'produit' => $repository->findAll(),'nb'=>$nb
        ]);
    }

    /**
     * @param $id
     * @param SessionInterface $session
     * @param ProduitRepository $repository
     * @Route("/product/single/{id}",name="product_single")
     */
    public function single($id,SessionInterface $session,ProduitRepository $repository){
        $panier= $session->get('panier',[]);
        $nb=0;
        foreach ($panier as $idd=>$quantite){
            $nb+=$quantite;

        }
        if(empty($panier[$id])){
            $panier[$id]=1;

        }


        return  $this->render('produit/single.html.twig',[
            'product'=>$repository->find($id),'nb'=>$nb,'quantity'=>$panier[$id]
        ]);


    }





}
