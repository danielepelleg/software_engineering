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
 * The client can have a name.
 *
 * It has an Employee attribute, which is set once the user sign into the machine with his username and password,
 * a boolean attribute showing the status of the user (if logged or not) and a Socket attribute, which is set once
 * the client establish a connection with the server.
 * Once the client has an employee logged, it takes his privileges in its attributes editRight and researchRight.
 */
public class Client {
    private static final int SPORT = 4444;
    private static final String SHOST = "localhost";
    private static final int MAX = 100;
    private static String name;

    private Employee user;
    private boolean logged;
    private boolean editRight;
    private boolean researchRight;

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
            this.logged = false;
            this.editRight = false;
        }
        catch (IOException e) {
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
     * Create and send a RequestLogin to the server
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
     * If so, set the Employee user in this class with the one sent by the server and grants him
     * the relative's permission.
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
            if (this.user.getMansion().equals(Mansion.Official)) this.editRight = true;
            if (this.user.getMansion().equals(Mansion.Director) || this.user.getMansion().equals(Mansion.Administrator)) this.researchRight = true;
        }
    }

    /**
     * Create and send a RequestAddEmployee to the server
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
            } else{
                System.out.println("You can't add a new Employee because you are not an Official");
                sendDefault();
            }
        }
        else sendDefault();
    }

    /**
     * Create and send a RequestResearch to the server
     *
     * @throws IOException Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void research() throws IOException, ClassNotFoundException {
        if (logged) {
            if (this.user.getMansion().equals(Mansion.Director) || (this.user.getMansion().equals(Mansion.Administrator))) {
                RequestResearch rq = new RequestResearch(this.user.getWorkplace(), this.user.getMansion());
                this.sendRequest(rq);
                ArrayList<Employee> employees = new ArrayList<>();
                employees = (ArrayList<Employee>) this.getResponse().getObject();
                for (Employee e : employees) {
                    System.out.println(e);
                }
            }
            else{
                System.out.println("You can't make a research because you are not an Administrator or a Director");
                sendDefault();
            }
        }
        else sendDefault();
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
        rq.setLogged(logged);
        rq.setEditRights(editRight);
        rq.setResearchRights(researchRight);
        this.sendRequest(rq);
        System.out.println(this.getResponse().getMessage());
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
        System.out.println(this.getResponse().getMessage());
        this.client.close();
    }

    /**
     * Send a Request to the client
     *
     * @param request the request to send
     * @throws IOException Input Output Exception, for the Stream
     */
    public void sendRequest(Request request) throws IOException {
        System.out.format("Client %s sends: %s to Server", name, request.getClass().getSimpleName());
        os.writeObject(request);
        os.flush();
    }

    /**
     * Create and send a RequestUpdateEmployee to the server
     *
     * @param currentUsername the current username of the employee to be updated
     * @param newName the new name of the employee to be updated
     * @param newSurname the new surname of the employee to be updated
     * @param newUsername the new username of the employee to be updated
     * @param newPassword the new password of the employee to be updated
     * @param newFiscalCode the new fiscal code of the employee to be updated
     * @param newWorkplace the new workplace of the employee to be updated
     * @param newMansion the new mansion the employee to updated
     * @param newStartActivity the new start-activity date of the employee to be updated
     * @param newEndActivity the new end-activity date of the employee to be updated
     *
     * @throws IOException Input Output Exception, for the Stream
     * @throws ClassNotFoundException if the Object is not an instance of Response
     */
    public void updateEmployee(String currentUsername, String newName, String newSurname, String newUsername, String newPassword, String newFiscalCode, Workplace newWorkplace, Mansion newMansion, String newStartActivity, String newEndActivity) throws IOException, ClassNotFoundException {
        if (logged) {
            if (this.user.getMansion().equals(Mansion.Official)) {
                RequestUpdateEmployee rq = new RequestUpdateEmployee(currentUsername, newName, newSurname, newUsername, newPassword, newFiscalCode, newWorkplace, newMansion, newStartActivity, newEndActivity);
                this.sendRequest(rq);
                System.out.println(this.getResponse().getMessage());
            } else{
                System.out.println("You can't update the Employee because you are not an Official");
                sendDefault();
            }
        }
        else sendDefault();
    }
}
