package it.unipr.fava_pellegrini;

/**
 * RequestUpdateEmployee Class - Request Subclass
 * Each RequestUpdateEmployee has the new attributes ot the employee to be updated.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class RequestUpdateEmployee extends Request {
    private Employee newEmployee;
    private String currentUsername;
    private String newName;
    private String newSurname;
    private String newFiscalCode;
    private Workplace newWorkplace;
    private Mansion newMansion;
    private String newStartActivity;
    private String newEndActivity;

    private String newUsername;
    private String newPassword;
    /**
     * Class constructor.
     * It updates the employee chosen by the client with the attributes given.
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
     */
    public RequestUpdateEmployee(final String currentUsername, final String newName, final String newSurname, final String newUsername, final String newPassword, final String newFiscalCode, final Workplace newWorkplace, final Mansion newMansion, final String newStartActivity, final String newEndActivity) {
        this.currentUsername = currentUsername;
        this.newName = newName;
        this.newSurname = newSurname;
        this.newFiscalCode = newFiscalCode;
        this.newWorkplace = newWorkplace;
        this.newMansion = newMansion;
        this.newStartActivity = newStartActivity;
        this.newEndActivity = newEndActivity;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.newEmployee = new Employee(newName, newSurname, newUsername, newPassword, newFiscalCode, newWorkplace, newMansion, newStartActivity, newEndActivity);
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    public String getNewFiscalCode() {
        return newFiscalCode;
    }

    public void setNewFiscalCode(String newFiscalCode) {
        this.newFiscalCode = newFiscalCode;
    }

    public Workplace getNewWorkplace() {
        return newWorkplace;
    }

    public void setNewWorkplace(Workplace newWorkplace) {
        this.newWorkplace = newWorkplace;
    }

    public Mansion getNewMansion() {
        return newMansion;
    }

    public void setNewMansion(Mansion newMansion) {
        this.newMansion = newMansion;
    }

    public String getNewStartActivity() {
        return newStartActivity;
    }

    public void setNewStartActivity(String newStartActivity) {
        this.newStartActivity = newStartActivity;
    }

    public String getNewEndActivity() {
        return newEndActivity;
    }

    public void setNewEndActivity(String newEndActivity) {
        this.newEndActivity = newEndActivity;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
