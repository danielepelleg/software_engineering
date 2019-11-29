package it.unipr.fava_pellegrini;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {
    private static final int MAX = 100;
    private static final long SLEEPTIME = 200;

    private Server server;
    private Socket socket;
    private boolean shutdown = false;

    public ServerThread(final Server s, final Socket c) {
        this.server = s;
        this.socket = c;
    }

    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;

        try {
            is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (!shutdown) {
            try {
                // take the request sent by the client on its output stream
                Object i = is.readObject();

                if (i instanceof Request) {
                    // convert the object in a request
                    Request rq = (Request) i;

                    String message = null;

                    RequestList command = RequestList.valueOf(i.getClass().getSimpleName());
                    System.out.println(command.toString());
                    switch (command) {
                        case RequestLogin:
                            RequestLogin requestLogin = (RequestLogin) rq;
                            message = login(requestLogin);
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response loginResponse = new Response("Please wait ... ", message);
                            loginResponse.setEmployee(getEmployeeRequested((RequestLogin) rq));
                            os.writeObject(loginResponse);
                            os.flush();
                            System.out.println(message);
                            break;
                        case RequestAddEmployee:
                            RequestAddEmployee requestAddEmployee = (RequestAddEmployee) rq;
                            message = createEmployee(requestAddEmployee);
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response addEmployeeResponse = new Response("Processing ... ", message);
                            os.writeObject(addEmployeeResponse);
                            os.flush();
                            System.out.println(message);
                            break;
                        case RequestResearch:
                            RequestResearch requestResearch = (RequestResearch) rq;
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response researchResponse = new Response("Processing ...", "");
                            researchResponse.setList(doResearch(requestResearch));
                            os.writeObject(researchResponse);
                            os.flush();
                            System.out.println(doResearch(requestResearch));
                            break;
                        case RequestUpdateEmployee:
                            RequestUpdateEmployee requestUpdateEmployee = (RequestUpdateEmployee) rq;
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response updateEmployeeResponse = new Response("Processing ...", this.updateEmployee(requestUpdateEmployee));
                            os.writeObject(updateEmployeeResponse);
                            os.flush();
                            System.out.println(this.updateEmployee(requestUpdateEmployee));
                            break;
                        case Request:
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response rs = new Response("Error", "Please Sign in!");
                            os.writeObject(rs);
                            os.flush();
                            break;
                        case RequestCloseConnection:
                            Thread.sleep(SLEEPTIME);
                            if (os == null) {
                                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                            }
                            Response closeConnectionResponse = new Response("Processing ...", "Connection Closed");
                            os.writeObject(closeConnectionResponse);
                            os.flush();
                            this.shutdown = true;
                            this.close();
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void close() {
        if (this.server.getPool().getActiveCount() == 1) {
            this.server.close();
        }
    }

    public String login(RequestLogin request) {
        for (Employee e : this.server.getEmployees()) {
            if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword()))
                return "Login Successful!";
        }
        return "Bad Login. Retry!";
    }

    public String createEmployee(RequestAddEmployee request) {
        if (checkFiscalCode(request.getNewEmployee())) {
            this.server.addEmployee(request.getNewEmployee());
            return "The employee has been added to the database!";
        } else
            return "The fiscal code of this employee is already registered in the database. Please check and try again!";
    }

    public String updateEmployee(RequestUpdateEmployee request) {
        //TODO se uno volesse fare l'update del codice fiscale controllare che non ce ne sia gia uno uguale nel DB
        for (Employee e : this.server.getEmployees()) {
            if (e.getUsername().equals(request.getNewEmployee().getUsername())) {
                e = request.getNewEmployee();
                return "The employee has been updated successfully!";
            }
        }
        return "Error the employee that you want to update isn't in the database, try again!";
    }


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

    public boolean checkFiscalCode(Employee newEmployee) {
        for (Employee e : this.server.employees) {
            if (e.getFiscalCode().equals(newEmployee.getFiscalCode())) {
                return false;
            }
        }
        return true;
    }

    public Employee getEmployeeRequested(RequestLogin request) {
        for (Employee e : this.server.getEmployees()) {
            if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword())) {
                return e;
            }
        }
        return null;
    }

}
