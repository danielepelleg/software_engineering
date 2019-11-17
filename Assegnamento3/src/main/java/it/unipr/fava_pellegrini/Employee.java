package it.unipr.fava_pellegrini;

import java.util.Date;

public class Employee {
    private String name;
    private String surname;
    private String fiscalCode;
    private Workplace workplace;
    private String mansion;
    private Date startActivity;
    private Date endActivity;

    public Employee(final String name, final String surname, final String fiscalCode, final Workplace workplace, final String mansion, final Date startActivity, final Date endActivity) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.workplace = workplace;
        this.mansion = mansion;
        this.startActivity = startActivity;
        this.endActivity = endActivity;
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

    public String getMansion() {
        return mansion;
    }

    public void setMansion(String mansion) {
        this.mansion = mansion;
    }

    public Date getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(Date startActivity) {
        this.startActivity = startActivity;
    }

    public Date getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(Date endActivity) {
        this.endActivity = endActivity;
    }
}
