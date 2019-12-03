package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Server Class
 * The client has the SPORT (Server Port) the COREPOOL, the MAXIMUM POOL and the IDLETIME as a Global variable.
 * It has a Socket attribute and a ThreadPoolExecutor which are set once an instance of the class is created.
 * It has two list, the first containing the employees and the second containing the workplaces.
 * This two list let the server work as a database.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Server {
    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 100;
    private static final long IDLETIME = 5000;

    private static final int SPORT = 4444;

    private ServerSocket socket;
    private ThreadPoolExecutor pool;
    private CopyOnWriteArrayList<Workplace> workplaces;
    private CopyOnWriteArrayList<Employee> employees;

    public CopyOnWriteArrayList<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(CopyOnWriteArrayList<Workplace> workplaces) {
        this.workplaces = workplaces;
    }

    public CopyOnWriteArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(CopyOnWriteArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Class default Constructor
     * Creates a server without employees and workplaces inside.
     */
    public Server() {
        try {
            this.socket = new ServerSocket(SPORT);
            System.out.println("Server is waiting on the port number: " + SPORT + "...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class constructor, initialize the server with the lists containing the instances
     * of employees and workplaces given.
     *
     * @param employees  the employees to be initialized
     * @param workplaces the workplaces to be initialized
     */
    public Server(CopyOnWriteArrayList<Employee> employees, CopyOnWriteArrayList<Workplace> workplaces) {
        try {
            this.socket = new ServerSocket(SPORT);
            System.out.println("Server is waiting on the port number " + SPORT + "...");
            this.employees = employees;
            this.workplaces = workplaces;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ThreadPoolExecutor getPool() {
        return this.pool;
    }


    public void addEmployee(Employee newEmployee) {
        this.employees.add(newEmployee);
    }

    public void addWorkplace(Workplace newWorkplace) {
        this.workplaces.add(newWorkplace);
    }

    /**
     * Create a string containing the list of the employees in the database
     *
     * @return the String
     */
    public String ListEmployee() {
        String result = "The employees are:\n\n";
        for (Employee e : this.employees) {
            result += e.toString() + "\n";
        }
        return result;
    }

    /**
     * Update an employee with a new one
     *
     * @param username    the username of the employee to be updated
     * @param newEmployee the new employee object
     */
    public void updateEmployee(String username, Employee newEmployee) {
        for (Employee e : this.employees) {
            if (e.getUsername().equals(username))
                e.setEmployee(newEmployee);
        }
    }

    /**
     * Start Server creating a socket and waiting for a client's connection
     */
    public void run() {
        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while (true) {
            try {
                Socket s = this.socket.accept();
                System.out.println("Connection accepted by: " + s.getInetAddress());
                this.pool.execute(new ServerThread(this, s));
            } catch (Exception e) {
                break;
            }
        }
        this.pool.shutdown();
    }

    /**
     * Close the socket through which the server receives the request.
     */
    public void close() {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1) The Server is initialized with some workplaces, and some users (workplace's employees). It's important
     * that every workplaces has at least one official.
     */
    public static void main(final String[] args) {
        CopyOnWriteArrayList<Workplace> workplaces = new CopyOnWriteArrayList<>();
        Workplace w1 = new Workplace("Barilla", "Via Mantova 166");
        Workplace w2 = new Workplace("Esselunga", "Via Emilia Ovest 230/A");
        Workplace w3 = new Workplace("Chiesi Farmaceutici S.p.A", "Via San Leonardo 96 A");
        workplaces.add(w1);
        workplaces.add(w2);
        workplaces.add(w3);

        Employee e1 = new Employee("Guido", "Barilla", "Guido", "farinaintegrale", "BRLGDU58L30G337M", w1, Mansion.Administrator, "1978-09-12", "2034-12-15");
        Employee e2 = new Employee("Pietro", "Barilla", "Peter", "pastalover", "BRLPTR98R10G337J", w1, Mansion.Official, "2019-09-16", "2052-12-31");
        Employee e3 = new Employee("Diletta", "Bighi", "Dile", "pandistelle", "DLTBGH76P46F205T", w1, Mansion.Official, "2014-03-03", "2032-06-18");
        Employee e4 = new Employee("Giacomo", "Pini", "Jack", "lemacine", "GCMPNI67S10A944S", w1, Mansion.Employee, "2011-11-03", "2030-12-03");

        Employee e5 = new Employee("Giuseppe", "Fabbri", "Beppe", "longS", "FBBGPP66A14F205P", w2, Mansion.Director, "1988-02-23", "2036-12-12");
        Employee e6 = new Employee("Silvia", "Moretti", "Silvietta", "S27ehcq0", "MRTSLV68M66L736S", w2, Mansion.Official, "1990-06-12", "2038-06-12");

        Employee e7 = new Employee("Claudio", "Marani", "Claus", "3cb49c90", "MRNCLD73D19A271V", w3, Mansion.Administrator, "2016-07-31", "2028-03-11");
        Employee e8 = new Employee("Ugo", "Di Francesco", "Frank", "pharman", "DFRGUO64B10G337G", w3, Mansion.Director, "1986-10-10", "2030-10-10");
        Employee e9 = new Employee("Gaia", "Madera", "Gaietta", "pharmawoman", "MDRGAI76S12F839D", w3, Mansion.Official, "2001-03-04", "2042-05-16");

        CopyOnWriteArrayList<Employee> employees = new CopyOnWriteArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        employees.add(e5);
        employees.add(e6);

        employees.add(e7);
        employees.add(e8);
        employees.add(e9);

        Server server = new Server(employees, workplaces);
        server.run();
    }
}
