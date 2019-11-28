package it.unipr.fava_pellegrini;

/**
 * RequestCloseConnection Class - Request Subclass
 * Each RequestLogin has a String containing the username, and a String containing the password the client
 * wants to sign in with.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class  RequestLogin extends Request{
    private final String username;
    private final String password;

    /**
     * Class Constructor.
     * It generates the the username and the password the client sends to the server to sign in
     * .
     * @param username the username of the employee
     * @param password the password of the employee
     */
    public RequestLogin( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
