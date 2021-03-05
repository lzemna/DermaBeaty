<?php

namespace App\Controller;

use App\Repository\ForumRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AdminController extends AbstractController
{
    /**
     * @Route("/adminPage", name="adminPage")
     */
    public function index(ForumRepository $repository): Response
    {
        return $this->render('admin/index.html.twig', [
            'forum' => $repository->findAll(),
        ]);
    }
    /**
     * @Route("/adminPage/forum/del/{id}", name="del_forum")
     */
    public function delForum($id,ForumRepository $repository): Response
    {
        $forum = $repository->find($id);
        $manager = $this->getDoctrine()->getManager();
        $manager->remove($forum);
        $manager->flush();
        return $this->redirectToRoute('adminPage');
    }
}
