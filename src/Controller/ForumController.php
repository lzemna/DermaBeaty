<?php

namespace App\Controller;

use App\data\SearchData;
use App\Entity\Forum;
use App\Entity\Reply;
use App\Entity\ReplyLike;
use App\Form\ForumType;
use App\Form\ReplyType;
use App\Form\SearchForm;
use App\Repository\ForumRepository;
use App\Repository\ReplyLikeRepository;
use App\Repository\ReplyRepository;
use App\Repository\SujetRepository;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use function Faker\Provider\DateTime;

class ForumController extends AbstractController
{
    /**
     * @Route("/forum", name="forum")
     */
    public function index(ForumRepository $repository,Request $request,SujetRepository $sujetRepository): Response
    {
        $data = new SearchData();
        $sujets = $sujetRepository->findAll();

        $data->page= $request->get('page',1);
        $form=$this->createForm(SearchForm::class,$data);
$form->handleRequest($request);

        return $this->render('forum/index.html.twig', [
            'controller_name' => 'ForumController','forum'=>$repository->selectData($data),'form'=>$form->createView(),'sujet'=>$sujets
        ]);
    }

    /**
     * @Route("/ala/aff/{id}", name="afficher_forum_with_cath")
     */
    public function forumFilter(ForumRepository $repository,Request $request,SujetRepository $sujetRepository,$id): Response
    {
        $data = new SearchData();
        $sujets = $sujetRepository->findAll();

        $data->page= $request->get('page',1);
        $form=$this->createForm(SearchForm::class,$data);
        $form->handleRequest($request);

        return $this->render('forum/index.html.twig', [
            'forum'=>$repository->selectDataWithSujet($data,$id),'form'=>$form->createView(),'sujet'=>$sujets
        ]);
    }

    /**
     * @param $id
     * @param ForumRepository $repository
     * @return Response
     * @Route ("/forum/show/{id}",name="forum_show")
     */
    public function showForum($id,ForumRepository $repository,Request $request){
        $forum = $repository->find($id);
        $reply = new Reply();
        $form = $this->createForm(ReplyType::class,$reply);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $manager = $this->getDoctrine()->getManager();
            $reply->setCreationDate(new \DateTime());
            $reply->setUser($this->getUser());
            $reply->setForum($forum);
            $manager->persist($reply);
            $manager->flush();

        }

        return $this->render('forum/show.html.twig',['forum'=>$forum,'form'=>$form->createView()]);





    }

    /**
     * @param ForumRepository $repository
     * @param $id
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route ("/forum/rem/{id}",name="delete_forum")
     */

    public function deleteForum(ForumRepository $repository,$id){
    $forum = $repository->find($id);
    $manager = $this->getDoctrine()->getManager();
    $manager->remove($forum);
    $manager->flush();
    return $this->redirectToRoute('forum');


    }



    /**
     * @param $id
     * @param Request $request
     * @param ForumRepository $repository
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("/forum/edit/{id}",name="edit_forum")
     */
    public function editForum($id,Request $request,ForumRepository $repository){
        $forum = $repository->find($id);
        $form = $this->createForm(ForumType::class,$forum);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $forum->setCreationDate(new \DateTime());
            $forum->setUser($this->getUser());
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('forum');

        }
        return $this->render('forum/newForum.html.twig',['form'=>$form->createView()]);



    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("/forum/add",name="add_forum")
     */
    public function AddForum(Request $request){
        $forum = new Forum();
        $form = $this->createForm(ForumType::class,$forum);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $forum->setCreationDate(new \DateTime());
            $forum->setUser($this->getUser());
            $em= $this->getDoctrine()->getManager();
            $em->persist($forum);
            $em->flush();
            return $this->redirectToRoute('forum');

        }
        return $this->render('forum/newForum.html.twig',['form'=>$form->createView()]);



    }

    /**
     * @param Reply $reply
     * @param ReplyLikeRepository $repository
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     * @Route("/reply/like/{id}",name="like_reply")
     */

    public function like(Reply $reply,ReplyLikeRepository $repository){
    $user = $this->getUser();

    $manager = $this->getDoctrine()->getManager();
    if(!$user){
        return $this->json(['status'=>'not authorised'],403);


    }

       else if($reply->isLikedByUser($user)){

           $like = $repository->findOneBy(['user'=>$user,'reply'=>$reply]);
            $manager->remove($like);
            $manager->flush();
           return $this->json(['like'=>'cotre like va etre supprimer','nblikes'=>$repository->count(['reply'=>$reply])],200);
       }
       else {
           $like = new ReplyLike();
$like->setReply($reply);
$like->setUser($user);
       $manager->persist($like);
        $manager->flush();
           return $this->json(['like'=>'vous avez likez ce reply','nblikes'=>$repository->count(['reply'=>$reply])],200);

       }





    }

    /**
     * @param $id
     * @param ReplyRepository $repository
     * @Route("/forum/reply/remove/{id}",name="deleteRep")
     */
    public function deleteReply($id , ReplyRepository $repository,ForumRepository $forumRepository){
        $reply = $repository->find($id);
        $ident = $reply->getForum()->getId();
        $manager = $this->getDoctrine()->getManager();
        $manager->remove($reply);
        $manager->flush();
        return $this->redirectToRoute('forum_show',['id'=>$ident]);
        //return $this->render('forum/show.html.twig',['forum'=>$forumRepository->find($ident)]);


    }

}
