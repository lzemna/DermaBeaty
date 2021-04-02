<?php

namespace App\Controller;

use App\Form\ContactMailType;
use App\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ContactController extends AbstractController
{
    /**
     * @Route("/contact", name="contact")
     */
    public function index(Request $request,\Swift_Mailer $mailer){
        {
            $form = $this->createForm(ContactMailType::class);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form-> isValid()){
                $contact = $form->getData();

                $message = (new \Swift_Message('Nouveau Contact'))
                ->setFrom($contact['mail'])
                ->setTo('votre@adresse.fr')

                ->setBody(
                    $this->renderView(
                        'emails/contact.html.twig', compact('contact')
                    ),
                    'text/html'

                )
                ;
                $mailer->send($message);
                $this->addFlash('message','mryygl saahbyyy ');
                return $this->redirectToRoute('contact');





        }
            return $this->render('contact/LivMail.html.twig', [
                'ContactForm' => $form->createview()
            ]);
        }




    }


}
