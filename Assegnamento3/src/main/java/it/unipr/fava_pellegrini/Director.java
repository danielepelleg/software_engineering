package it.unipr.fava_pellegrini;

import java.util.Date;

public class Director extends Official {
    boolean adminPrivilege;

    public Director(String username, String password, String name, String surname, String fiscalCode, Workplace workplace, String mansion, Date startActivity, Date endActivity) {
        super(username, password, name, surname, fiscalCode, workplace, mansion, startActivity, endActivity);
        this.adminPrivilege = false;
    }
}
