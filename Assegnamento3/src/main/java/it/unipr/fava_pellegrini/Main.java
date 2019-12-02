package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 100;
    private static final long IDLETIME = 5000;
    private ThreadPoolExecutor pool;

    private void run() {
        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while (true)
        {
            try
            {
                this.pool.execute(new TestThread1());
            }
            catch (Exception e)
            {
                break;
            }
        }
        this.pool.shutdown();
    }
    public static void main(final String[] args) throws IOException, ClassNotFoundException{
        new Main().run();

        Workplace w = new Workplace("Industria", "Via Mazzini 90");
        Client c1 = new Client("C1");
        Client c2 = new Client("C2");
        Client c3 = new Client("C3");
        Client c4 = new Client("C4");

        /*ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        clients.add(c4);
        Random r = new Random();
        int max = clients.size() - 1;
        int min = 0;
        for(int i = 0; i<6; i++){
            doOperations1(clients.get(r.nextInt((max - min) + 1) + min));
            doOperations2(clients.get(r.nextInt((max - min) + 1) + min));
            doOperations3(clients.get(r.nextInt((max - min) + 1) + min));
        }*/

        c1.login("Peter","pastalover");
        c2.login("Dile", "pandistelle");
        c3.login("Beppe", "longS");
        c4.login("Gaietta", "pharmawoman");
        //c1.login("Guido","farinaintegrale");
        c1.addEmployee("Marco", "Rossi", "Mark", "1234", "MRCRSI67S10A944S", w, Mansion.Employee, "2011-11-03", "2030-12-03");
        System.out.println("------------");
        //c1.research();
        c1.closeConnection();
        System.out.println("------------");
        c2.research();
        System.out.println("------------");
        c3.closeConnection();
        c2.closeConnection();
        c4.closeConnection();

    }
/*
    public static void doOperations1(Client client) throws IOException, ClassNotFoundException {
        Workplace w = new Workplace("Ferrero", "Via Mazzini 90");
        client.login("Peter","pastalover");
        client.addEmployee("Marco", "Rossi", "Mark", "1234", "MRCRSI67S10A944S", w, Mansion.Employee, "2011-11-03", "2030-12-03");
        client.addEmployee("Riccardo", "Verdi", "Rick", "56789", "RCRVRD98S16H770P", w, Mansion.Employee, "2015-11-03", "2034-12-03");
        client.closeConnection();
    }

    public static void doOperations2(Client client) throws IOException, ClassNotFoundException {
        client.login("Dile", "pandistelle");
        client.research();
        client.closeConnection();
    }

    public static void doOperations3(Client client) throws IOException, ClassNotFoundException {
        client.login("Beppe", "longS");
        client.research();
        client.closeConnection();
    }
*/
    public static class TestThread1 implements Runnable{
        @Override
        public void run() {
            try {
                Workplace w = new Workplace("Ferrero", "Via Mazzini 90");
                Client client = new Client("C1");
                client.login("Peter","pastalover");
                client.addEmployee("Marco", "Rossi", "Mark", "1234", "MRCRSI67S10A944S", w, Mansion.Employee, "2011-11-03", "2030-12-03");
                client.addEmployee("Riccardo", "Verdi", "Rick", "56789", "RCRVRD98S16H770P", w, Mansion.Employee, "2015-11-03", "2034-12-03");
                client.closeConnection();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
