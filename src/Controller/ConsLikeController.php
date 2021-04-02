<?php

namespace App\Controller;

use App\Entity\Centre;
use App\Entity\CentreLike;
use App\Entity\ConsLike;
use App\Repository\CentreLikeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ConsLikeController extends AbstractController
{
    /**
     * @Route("/cons/like", name="cons_like")
     */
    public function index(): Response
    {
        return $this->render('cons_like/index.html.twig', [
            'controller_name' => 'ConsLikeController',
        ]);
    }
    /**
     * @param Reply $reply
     * @param ReplyLikeRepository $repository
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     * @Route("/centre/like/{id}",name="like_centre")
     */

    public function like(Centre $centre,CentreLikeRepository $repository){
        $user = $this->getUser();

        $manager = $this->getDoctrine()->getManager();
        if(!$user){
            return $this->json(['status'=>'not authorised'],403);


        }

        else if($centre->isLikedByUser($user)){

            $like = $repository->findOneBy(['user'=>$user,'centre'=>$centre]);
            $manager->remove($like);
            $manager->flush();
            return $this->json(['like'=>'cotre like va etre supprimer','nblikes'=>$repository->count(['centre'=>$centre])],200);
        }
        else {
            $like = new CentreLike();
            $like->setCentre($centre);
            $like->setUser($user);
            $manager->persist($like);
            $manager->flush();
            return $this->json(['like'=>'vous avez likez ce reply','nblikes'=>$repository->count(['centre'=>$centre])],200);

        }





    }
    /**
     * @Route("/affichL", name="affichL")
     */
    public function list()
    {
        $likes= $this->getDoctrine()->getRepository(ConsLike::class)->findAll();

        return $this->render('cons_like/index.html.twig', [
            "likes" => $likes,]);

    }

}
