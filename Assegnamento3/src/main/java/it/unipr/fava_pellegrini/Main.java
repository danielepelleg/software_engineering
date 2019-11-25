package it.unipr.fava_pellegrini;

import java.io.IOException;

/**
 * Main Class
 * Test, through a simulation, the classes and methods created
 *
 * 1) The Company is initialized with some workplaces, and personal data of some users. It's important
 *      that every workplaces has at least a official.
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
    public static void main(final String[] args) throws IOException, ClassNotFoundException{
        Client c1 = new Client();
        Client c2 = new Client();
        c1.connect();
        c2.connect();
        c1.login("Gino","1234");
        c2.login("Gino","1234");
        c1.closeConnection();
        c2.closeConnection();
    }
}
