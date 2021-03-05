<?php

namespace App\Command;

use App\Entity\User;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputArgument;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Input\InputOption;
use Symfony\Component\Console\Output\OutputInterface;
use Symfony\Component\Console\Style\SymfonyStyle;
use Doctrine\ORM\EntityManagerInterface;

class UserPromoteCommand extends Command
{
    protected static $defaultName = 'app:user:promote';
    protected static $defaultDescription = 'Add a short description for your command';
    private $em;

    public function __construct(EntityManagerInterface $em)
    {
        $this->em = $em;

        parent::__construct();
    }
    protected function configure()
    {
        $this

            ->addArgument('email', InputArgument::REQUIRED, 'Email address of the user you want to promote.')
            ->addArgument('roles', InputArgument::REQUIRED, 'The roles you want to add to the user.')
        ;
    }

    protected function execute(InputInterface $input, OutputInterface $output): int
    {
        $io = new SymfonyStyle($input, $output);
        $email = $input->getArgument('email');
        $roles = $input->getArgument('roles');
        $userRepository = $this->em->getRepository(User::class);
        $user = $userRepository->findOneByEmail($email);

        if ($user) {
            $user->addRoles($roles);
            $this->em->flush();

            $io->success('The roles has been successfully added to the user.');
        }

        if ($input->getOption('option1')) {
            $io->error('There is no user with that email address.');
        }

        $io->success('You have a new command! Now make it your own! Pass --help to see your options.');

        return Command::SUCCESS;
    }
}
