package it.unipr.fava_pellegrini;

import java.io.Serializable;

/**
 * Employee Class
 * Each employee has the name attribute, the surname, the username, the fiscal code, the workplace, the mansion,
 * a string containing the date he started the activity and a date in which he has to end it.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
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

    /**
     * Class constructor.
     *
     * @param name the name of the employee to be created
     * @param surname the surname of the employee to be created
     * @param username the username of the employee to be created
     * @param password the password of the employee to be created
     * @param fiscalCode the fiscal code of the employee to be created
     * @param workplace the workplace in which the employee works in
     * @param mansion the mansion the employee holds in the society
     * @param startActivity the date the employee started to work
     * @param endActivity the date the contract of the employee will end
     */
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

    public void setEmployee(Employee e){
        setName(e.getName());
        setSurname(e.getSurname());
        setUsername(e.getUsername());
        setPassword(e.getPassword());
        setFiscalCode(e.getFiscalCode());
        setWorkplace(e.getWorkplace());
        setMansion(e.getMansion());
        setStartActivity(e.getStartActivity());
        setEndActivity(e.getEndActivity());
    }

    /**
     * Return a string showing the employee's attributes
     *
     * @return String the string
     *
     */
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
