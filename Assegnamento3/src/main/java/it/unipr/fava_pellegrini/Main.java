package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Main Class
 * Test, through a simulation, the classes and methods created
 *
 * 2) Randomly, every official adds or updates some employees. The sequence of every action must be broken
 *      with a random timed wait. Once a new fiscal code is generated, it is compared to the already existing ones.
 * 3) Randomly, every director and administrator does a research on the list of employees of a workplace.
 *      The sequence of every action must be broken with a random timed wait.
 * 4) The simulation test will be over after a random timed timer. This timer must ensure a right time to let
 *      every actor does a right number of actions to test the system and to ensure a good concurrency management.
 * 5) The server will shut down the service when all the tests of the clients will be over and they will have
 *      shut down the communication with the server itself.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Main {
    public static void main(final String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Client c1 = new Client();
        System.out.println("Insert your username:");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println("Insert your password:");
        String password = sc.nextLine();
        c1.login(username, password);
        System.out.println("-----------------------------------------------");
        System.out.println("Add employee");
        String nameEmployee;
        String surnameEmployee;
        String fiscalCodeEmployee;
        String workplaceEmployee;
        String mansionEmployee;
        String startActivityEmployee;
        String endActivityEmployee;
        System.out.println("Insert the name of the employee:");
        sc.nextLine();




    }
}

