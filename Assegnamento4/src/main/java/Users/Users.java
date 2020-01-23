package Users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Users Class
 * This class is used to display the data about the users and do operations on them
 * in the TableView on Users.fxml
 */
public class Users {
    private final StringProperty userName;
    private final StringProperty userSurname;
    private final StringProperty userUsername;
    private final StringProperty userType;
    private final StringProperty userPassword;

    /**
     * Class Constructor
     *
     * @param userName the name of the user
     * @param userSurname the surname of the user
     * @param userUsername the username of the user
     * @param userPassword the password of the user
     * @param userType the type of the user, "Admin" or "Member"
     *
     */
    public Users(String userName, String userSurname, String userUsername, String userType, String userPassword) {
        this.userName = new SimpleStringProperty(userName);
        this.userSurname = new SimpleStringProperty(userSurname);
        this.userUsername = new SimpleStringProperty(userUsername);
        this.userType = new SimpleStringProperty(userType);
        this.userPassword = new SimpleStringProperty(userPassword);
    }

    /**
     * Class Getters e Setters
     */

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserSurname() {
        return userSurname.get();
    }

    public StringProperty userSurnameProperty() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname.set(userSurname);
    }

    public String getUserUsername() {
        return userUsername.get();
    }

    public StringProperty userUsernameProperty() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername.set(userUsername);
    }

    public String getUserType() {
        return userType.get();
    }

    public StringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }
    public String getUserPassword() {
        return userPassword.get();
    }

    public StringProperty userPasswordProperty() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword.set(userPassword);
    }
}

