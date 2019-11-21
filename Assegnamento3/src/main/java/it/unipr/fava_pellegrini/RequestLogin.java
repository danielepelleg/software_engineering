package it.unipr.fava_pellegrini;

public class  RequestLogin extends Request{
    private final String username;
    private final String password;

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
