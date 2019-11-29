package it.unipr.fava_pellegrini;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Client Class
 * The client has the SPORT (Server Port) and SHOST (Server address) as a Global variable.
 * <p>
 * It has an Employee attribute, which is set once the user sign into the machine with his username and password,
 * a boolean attribute showing the status of the user (if logged or not) and a Socket attribute, which is set once
 * the client establish a connection with the server.
 */
public class Client {
    private static final int SPORT = 4444;
    private static final String SHOST = "localhost";
    private static final int MAX = 100;

    private Employee user;
    private boolean logged = false;

    private Socket client;

    ObjectOutputStream os;
    ObjectInputStream is;

    /**
     * Open a socket connection with the server, and creates the input and the output
     * channel through which the client send and receives objects.
     */
    public void connect() {
        try {
            client = new Socket(SHOST, SPORT);
            this.os = new ObjectOutputStream(client.getOutputStream());
            this.is = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the Server response.
     *
     * @return the response of the server
     * @throws IOException            Input Output Exception, for the stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public Response getResponse() throws IOException, ClassNotFoundException {
        if (is == null) {
            is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        }
        Object o = is.readObject();

        if (o instanceof Response) {
            Response rs = (Response) o;
            System.out.format(" and received: %s from Server%n", rs.getValue());
            return rs;
        }
        return null;
    }

    /**
     * Send a RequestLogin to the server
     *
     * @param username the username the user wants to sign in with
     * @param password the password
     * @throws IOException            Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void login(String username, String password) throws IOException, ClassNotFoundException {
        RequestLogin rq = new RequestLogin(username, password);
        this.sendRequest(rq);
        this.checkLogin(this.getResponse());
    }

    /**
     * Take the response of the Server to the RequestLogin sent by the Client and check if everything went well.
     * If so, set the Employee user in this class with the one sent by the server.
     *
     * @param response the response sent by the server
     */
    public void checkLogin(Response response) {
        if (response.getMessage().equals("Bad Login. Retry!")) {
            System.out.println("You are not logged into the server. You must be logged to make researches.");
            this.logged = false;
        }
        if (response.getMessage().equals("Login Successful!")) {
            this.user = (Employee) response.getObject();
            System.out.println("You are now logged as\n" + this.user.toString());
            this.logged = true;
        }
    }

    /**
     * Send a RequestAddEmployee to the server
     *
     * @param name the name of the new employee to be created
     * @param surname the surname of the new employee to be created
     * @param username the username of the new employee to be created
     * @param password the password of the new employee to be created
     * @param fiscalCode the fiscal code of the new employee to be created
     * @param workplace the workplace in which the new employee works in
     * @param mansion the mansion the new employee holds in the society
     * @param startActivity the date the new employee started to work
     * @param endActivity the date the contract of the new employee will end
     * @throws IOException            Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void addEmployee(String name, String surname, String username, String password, String fiscalCode, Workplace workplace, Mansion mansion, String startActivity, String endActivity) throws IOException, ClassNotFoundException {
        if (logged) {
            if (this.user.getMansion().equals(Mansion.Official)) {
                RequestAddEmployee rq = new RequestAddEmployee(name, surname, username, password, fiscalCode, workplace, mansion, startActivity, endActivity);
                this.sendRequest(rq);
                System.out.println(this.getResponse().getMessage());
            } else System.out.println("You can't add a new Employee because you are not an Official");
        } else {
            sendDefault();
        }
    }

    /**
     * Send a RequestResearch to the server
     *
     * @throws IOException Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void research() throws IOException, ClassNotFoundException {
        if (logged) {
            if (this.user.getMansion().equals(Mansion.Director) || (this.user.getMansion().equals(Mansion.Administrator))) {
                RequestResearch rq = new RequestResearch(this.user.getWorkplace(), this.user.getMansion());
                this.sendRequest(rq);
                ArrayList<Employee> employees = new ArrayList<Employee>();
                employees = (ArrayList<Employee>) this.getResponse().getObject();
                for (Employee e : employees) {
                    System.out.println(e);
                }
            } else System.out.println("You can't make a research because you are not an Administrator or a Director");
        }
        sendDefault();
    }

    /**
     * Send an empty default request.
     * It is used when the client is not logged and wants to perform actions it can't request.
     * When the Server reads this request, send a message to the client and ask him to sign in.
     *
     * @throws IOException Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void sendDefault() throws IOException, ClassNotFoundException {
        Request rq = new Request();
        this.sendRequest(rq);
        System.out.println(this.getResponse().getValue());
    }

    /**
     * Close the connection of the Client with the Server.
     *
     * @throws IOException Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void closeConnection() throws IOException, ClassNotFoundException {
        RequestCloseConnection rq = new RequestCloseConnection();
        this.sendRequest(rq);
        System.out.println(this.getResponse().getValue());
        this.client.close();
    }

    public void sendRequest(Request request) throws IOException {
        System.out.format("Client sends: %s to Server", request.getClass().getSimpleName());
        os.writeObject(request);
        os.flush();
    }

    public void updateEmployee(String name, String surname, String username, String password, String fiscalCode, Workplace workplace, Mansion mansion, String startActivity, String endActivity) throws IOException, ClassNotFoundException {
        RequestUpdateEmployee rq = new RequestUpdateEmployee(name, surname, username, password, fiscalCode, workplace, mansion, startActivity, endActivity);
        this.sendRequest(rq);
        System.out.println(this.getResponse().getMessage());
    }
}
