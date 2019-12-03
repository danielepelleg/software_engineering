package it.unipr.fava_pellegrini;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * ServerThread Class
 * Manage the connection open by the client on a socket and accepted by the server through a system of threads
 * The ServerThread as the MAXIMUM SLEEPTIME and the MINIMUM SLEEPTIME as a Global variable.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class ServerThread implements Runnable {
    private static final int MAX_SLEEPTIME = 1600;
    private static final int MIN_SLEEPTIME = 1000;

    private Server server;
    private Socket socket;
    private boolean shutdown;
    ObjectInputStream is;
    ObjectOutputStream os;
    private Employee user;
    private boolean userLogged;

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public boolean isUserLogged() {
        return userLogged;
    }

    public void setUserLogged(boolean userLogged) {
        this.userLogged = userLogged;
    }

    /**
     * Class Constructor
     *
     * @param s the server on which the thread execute tasks
     * @param c the socket on which the thread execute tasks
     */
    public ServerThread(final Server s, final Socket c) {
        this.server = s;
        this.socket = c;
        this.shutdown = false;
        this.is = null;
        this.os = null;
        this.userLogged = false;
    }

    /**
     * Start the Server Thread
     * Read the object sent by the Client, cast it in a Request sent by the Client and send the relative's Response
     */
    @Override
    public void run() {
        try {
            is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (!shutdown) {
            try {
                Object i = is.readObject();

                if (i instanceof Request) {
                    Request rq = (Request) i;
                    Response response;
                    RequestList command = RequestList.valueOf(i.getClass().getSimpleName());
                    System.out.println(command.toString());
                    switch (command) {
                        case RequestLogin -> {
                            RequestLogin requestLogin = (RequestLogin) rq;
                            if(!this.isUserLogged()){
                                if(login(requestLogin).equals("Login Successful!")){
                                    response = new Response("Operation processed", login(requestLogin));
                                    response.setEmployee(getEmployeeRequested((RequestLogin) rq));
                                    this.setUserLogged(true);
                                    this.setUser(getEmployeeRequested((RequestLogin) rq));
                                    sendResponse(response);
                                } else {
                                    response = new Response("ERROR", login(requestLogin));
                                    sendResponse(response);
                                }
                            } else {
                                response = new Response("ERROR", "You are already logged!");
                                sendResponse(response);
                            }
                        }
                        case RequestAddEmployee -> {
                            RequestAddEmployee requestAddEmployee = (RequestAddEmployee) rq;
                            if(this.isUserLogged()){
                                if(checkPermissions(requestAddEmployee)){
                                    response = new Response("Operation processed", createEmployee(requestAddEmployee));
                                    sendResponse(response);
                                }
                                else {
                                    response = new Response("ERROR", "You must be an Official to this operation!");
                                    sendResponse(response);
                                }
                            } else {
                                response = new Response("ERROR", "You must be logged in to do this operation! Make the login and retry...");
                                sendResponse(response);
                            }
                        }
                        case RequestResearch -> {
                            RequestResearch requestResearch = (RequestResearch) rq;
                            if(this.isUserLogged()){
                                if(checkPermissions(requestResearch)){
                                    response = new Response("Operation processed", "");
                                    response.setList(doResearch(requestResearch));
                                    response.setMessageListed();
                                    sendResponse(response);
                                }
                                else {
                                    response = new Response("ERROR", "You must be an Administrator or a Director to this operation!");
                                    sendResponse(response);
                                }
                            } else {
                                response = new Response("ERROR", "You must be logged in to do this operation! Make the login and retry...");
                                sendResponse(response);
                            }
                        }
                        case RequestUpdateEmployee -> {
                            RequestUpdateEmployee requestUpdateEmployee = (RequestUpdateEmployee) rq;
                            if(this.isUserLogged()){
                                if(checkPermissions(requestUpdateEmployee)){
                                    response = new Response("Operation processed", updateEmployee(requestUpdateEmployee));
                                    sendResponse(response);
                                }
                                else {
                                    response = new Response("ERROR", "You must be an Official to this operation!\n");
                                    sendResponse(response);
                                }
                            } else {
                                response = new Response("ERROR", "You must be logged in to do this operation! Make the login and retry...\n");
                                sendResponse(response);
                            }
                        }
                        case RequestCloseConnection -> {
                            response = new Response("Operation processed", "Connection Closed");
                            sendResponse(response);
                            this.shutdown = true;
                            close();
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + command);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    /**
     * Send a response to the CLient
     *
     * @param response the response to be sent
     */
    public void sendResponse(Response response){
        try {
            randomSleep();
            if (os == null) {
                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
            }
            os.writeObject(response);
            os.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sign the Client in to the server
     *
     * @param request the RequestLogin of the client
     * @return the result of the Login, whether it worked or not
     */
    public String login(RequestLogin request) {
        for (Employee e : this.server.getEmployees()) {
            if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword()))
                return "Login Successful!";
        }
        return "Bad Login. Retry!";
    }

    /**
     * Create a new Employee in the Server
     *
     * @param request the RequestAddEmployee containing the new Employee to add
     * @return the result of the elaboration of the request, whether it worked or not
     */
    public String createEmployee(RequestAddEmployee request) {
        if (checkFiscalCode(request.getNewEmployee())) {
            this.server.addEmployee(request.getNewEmployee());
            return "The employee has been added to the database!";
        } else
            return "The fiscal code of this employee is already registered in the database. Please check and try again!";
    }

    /**
     * Update an employee in the Server.
     *
     * @param request the RequestUpdateEmployee containing the new Employee to update
     * @return the result of the elaboration of the request, whether it worked or not
     */
    public String updateEmployee(RequestUpdateEmployee request) {
        for (Employee e : this.server.getEmployees()) {
            if (e.getUsername().equals(request.getCurrentUsername())) {
                if(checkUpdate(request.getNewEmployee())) {
                    this.server.updateEmployee(request.getCurrentUsername(), request.getNewEmployee());
                    return "The employee has been updated successfully!\n";
                }
                else return "The new fiscal code of the employee you want to update is already present in the database!";
            }
        }
        return "Error the employee you want to update isn't in the database, try again!";
    }

    /**
     * Research the employees request by the Client
     *
     * @param request the RequestResearch containing the Workplace through which filter the research.
     * @return a list containing the employees of the workplace requested
     */
    public ArrayList<Employee> doResearch(RequestResearch request) {
        ArrayList<Employee> listEmployees = new ArrayList<Employee>();
        if (request.getMansion().equals(Mansion.Director)) {
            for (Employee e : this.server.getEmployees()) {
                if (request.getWorkplace().getName().equals(e.getWorkplace().getName()) && request.getWorkplace().getAddress().equals(e.getWorkplace().getAddress())) {
                    if (e.getMansion() != Mansion.Administrator) {
                        listEmployees.add(e);
                    }
                }
            }
        } else if (request.getMansion().equals(Mansion.Administrator)) {
            for (Employee e : this.server.getEmployees()) {
                if (e.getWorkplace().getName().equals(request.getWorkplace().getName()) && e.getWorkplace().getAddress().equals(request.getWorkplace().getAddress())) {
                    listEmployees.add(e);
                }
            }
        }
        return listEmployees;
    }

    /**
     * Check if the fiscal code of a new Employee is already present in the database
     *
     * @param newEmployee the newEmployee to check
     * @return true if not present, false if already present
     */
    public boolean checkFiscalCode(Employee newEmployee) {
        for (Employee e : this.server.getEmployees()) {
            if (e.getFiscalCode().equals(newEmployee.getFiscalCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the updated employee has a correct fiscal code to be inserted in the Server.
     *
     * @param newEmployee the employee of which fiscal code must be checked
     * @return true if the operation of updating is permitted, false otherwise
     */
    public boolean checkUpdate(Employee newEmployee){
        ArrayList<Employee> checkingList = new ArrayList<>();
        for(Employee e : this.server.getEmployees()){
            if(e != newEmployee)
                checkingList.add(e);
        }
        for(Employee e : checkingList){
            if(e.getFiscalCode().equals(newEmployee.getFiscalCode()))
                return false;
        }
        return true;
    }

    /**
     * Return the employee requested by the RequestLogin
     *
     * @param request the RequestLogin containing the username and password to sign in
     * @return the employee requested if inside the database
     */
    public Employee getEmployeeRequested(RequestLogin request) {
        for (Employee e : this.server.getEmployees()) {
            if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword())) {
                return e;
            }
        }
        return null;
    }

    /** Causes the currently executing thread to sleep (temporarily cease execution)
     * for a specified number between 1000 (MINIMUM SLEEP TIME) and 1600 (MAXIMUM SLEEP TIME) milliseconds
     */
    public void randomSleep(){
        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(MAX_SLEEPTIME-MIN_SLEEPTIME)+MIN_SLEEPTIME);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Close the server if there's just one thread that is actively executing tasks.
     */
    public void close() {
        if (this.server.getPool().getActiveCount() == 1) {
            this.server.close();
        }
    }

    public boolean checkPermissions(Request request){
        RequestList command = RequestList.valueOf(request.getClass().getSimpleName());
        switch (command){
            case RequestAddEmployee, RequestUpdateEmployee -> {
                if(this.getUser().getMansion().equals(Mansion.Official)){
                    return true;
                } else return false;
            }
            case RequestResearch -> {
                if(this.getUser().getMansion().equals(Mansion.Administrator) || this.getUser().getMansion().equals(Mansion.Director)){
                    return true;
                } else return false;
            }
            default -> {
                return false;
            }
        }
    }
}
