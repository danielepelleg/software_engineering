package it.unipr.fava_pellegrini;

import java.util.Date;

public class Official extends Employee {
    private String username;
    private String password;
    private String hashed_password;

    public Official(final String username, final String password, String name, String surname, String fiscalCode, Workplace workplace, String mansion, Date startActivity, Date endActivity) {
        super(name, surname, fiscalCode, workplace, mansion, startActivity, endActivity);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }
}
