package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.util.Scanner;

/**
 * Simulation Class
 * Launch an automated simulation that shows all the cases managed by the server. For every action performed by the client,
 * the simulation shows the cases where the client passes wrong inputs to the server and the ones where the inputs are fine.
 *
 * The client sign-in as an Administrator or as an Official. There are two run() methods.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Simulation {
    private Client client = new Client();
    private Workplace w1 = new Workplace("Barilla", "Via Mantova 166");

    /**
     * CLass Constructor
     *
     */
    Simulation(){}

    /**
     * Simulate a login with wrong credentials.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void failLogin() throws IOException, ClassNotFoundException {
        this.client.login("Guido", "FARINAINTEGRALE");
        printLine();
    }

    /**
     * Simulate a successful login.
     * If called twice or more, the Server sends an Error message warning the user he is already logged in.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void login() throws IOException, ClassNotFoundException{
        this.client.login("Guido", "farinaintegrale");
        printLine();
    }

    /**
     * Simulate the creation of an already existing employee.
     *
     */
    public void addExistingEmployee() throws IOException, ClassNotFoundException {
        this.client.addEmployee("Giacomo", "Pini", "Jack", "lemacine", "GCMPNI67S10A944S", w1, Mansion.Employee, "2011-11-03", "2030-12-03");
        printLine();
    }

    /**
     * Simulate the creation of a new employee.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void addEmployee() throws IOException, ClassNotFoundException {
        this.client.addEmployee("Maria", "Ghidini", "Mery", "pass1234", "MRLPTR01R10G337J", w1, Mansion.Director, "2019-12-16", "2026-12-31");
        printLine();
    }

    /**
     * Simulate the update of an employee who doesn't exists inside the server
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void updateNotExistingEmployee() throws IOException, ClassNotFoundException {
        this.client.updateEmployee("Carlo", "Luca", "Barilla", "Luke", "secondson", "BRLCAR02R10G337Y", w1, Mansion.Employee, "2019-12-16", "2036-12-31");
        printLine();
    }

    /**
     * Simulate the update of an existing employee changing his fiscal code with an already existing one.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void updateExistingEmployee() throws IOException, ClassNotFoundException {
        this.client.updateEmployee("Peter", "Luca", "Barilla", "Luke", "secondson", "BRLPTR98R10G337J", w1, Mansion.Employee, "2019-12-16", "2036-12-31");
        printLine();
    }

    /**
     * Simulate the update of a new employee
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void updateEmployee() throws IOException, ClassNotFoundException {
        this.client.updateEmployee("Peter", "Luca", "Barilla", "Luke", "secondson", "BRLCAR02R10G337Y", w1, Mansion.Employee, "2019-12-16", "2036-12-31");
        printLine();
    }

    /**
     * Simulate a research of the employees belonging to the same workplace as the logged user.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void research() throws IOException, ClassNotFoundException {
        this.client.research();
        printLine();
    }

    /**
     * Simulate a Request of CLose connection from the client
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void close() throws IOException, ClassNotFoundException {
        this.client.closeConnection();
        printLine();
    }

    /**
     * Shows all the actions that an Administrator can perform, and their relative errors management.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void runAdministrator() throws IOException, ClassNotFoundException {
        System.out.println("This simulation shows the actions that an Administrator can perform, and their relative errors management:");
        failLogin();
        login();
        login();
        addExistingEmployee();
        addEmployee();
        updateNotExistingEmployee();
        updateExistingEmployee();
        updateEmployee();
        research();
        close();
    }

    /**
     * This method show what happens when someone with non-full administrator privileges tries to perform an action
     * he can't handle.
     *
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void runOfficial() throws IOException, ClassNotFoundException {
        System.out.println("This method shows what happens when an official, " +
                "who doesn't have full admin privilege,\n " +
                "tries to perform a Director-Administrator action: the Research");
        this.client.login("Gaietta", "pharmawoman");
        this.client.research();
        this.client.closeConnection();
    }

    /**
     * Print lines as action separators
     */
    public void printLine(){
        System.out.println("---------------------------------------------");
    }

    /**
     * The tester can choose which simulation run. By default, runs the first one.
     * - Simulation().runAdministrator()
     * - Simulation().runOfficial()
     *
     * @param args arguments
     * @throws IOException Input-Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public static void main(final String[] args) throws IOException, ClassNotFoundException{
        new Simulation().runAdministrator();
    }
}
