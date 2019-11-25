package it.unipr.fava_pellegrini;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private String name;
    private String surname;
    private String fiscalCode;
    private Workplace workplace;
    private Mansion mansion;
    private String startActivity;
    private String endActivity;

    private String username;
    private String password;
    private String hashed_password;

    public Employee(final String name, final String surname, final String username, final String password, final String fiscalCode, final Workplace workplace, final Mansion mansion, final String startActivity, final String endActivity) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.workplace = workplace;
        this.mansion = mansion;
        this.startActivity = startActivity;
        this.endActivity = endActivity;

        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Mansion getMansion() {
        return mansion;
    }

    public void setMansion(Mansion mansion) {
        this.mansion = mansion;
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

    public String getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(String startActivity) {
        this.startActivity = startActivity;
    }

    public String getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(String endActivity) {
        this.endActivity = endActivity;
    }

    @Override
    public String toString(){
        return  "Name: " + this.name + "\n" +
                "Surname: " + this.surname + "\n" +
                "Username: " + this.username + "\n" +
                "Fiscal Code: " + this.fiscalCode + "\n" +
                "Workplace: " + this.workplace.toString() + "\n" +
                "Mansion: " + this.mansion.toString() + "\n" +
                "Start Activity Date: " + this.startActivity + "\n" +
                "End Activity Date: " + this.endActivity + "\n";

    }
}
