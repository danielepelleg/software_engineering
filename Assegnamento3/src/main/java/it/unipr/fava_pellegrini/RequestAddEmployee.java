package it.unipr.fava_pellegrini;

public class RequestAddEmployee extends Request{
    private Employee newEmployee;
    private String name;
    private String surname;
    private String fiscalCode;
    private Workplace workplace;
    private Mansion mansion;
    private String startActivity;
    private String endActivity;

    private final String username;
    private final String password;

    public RequestAddEmployee(final String name, final String surname, final String username, final String password, final String fiscalCode, final Workplace workplace, final Mansion mansion, final String startActivity, final String endActivity) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.workplace = workplace;
        this.mansion = mansion;
        this.startActivity = startActivity;
        this.endActivity = endActivity;
        this.username = username;
        this.password = password;
        this.newEmployee = new Employee(name, surname, username, password, fiscalCode, workplace, mansion, startActivity, endActivity);
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
}
