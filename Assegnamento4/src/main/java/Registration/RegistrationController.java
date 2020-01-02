package Registration;

import AlertBox.WarningBox;
import SportClub.Admin;
import SportClub.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * RegistrationController Class
 * Controls the Registration.fxml events.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class RegistrationController {

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button backLoginButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private CheckBox adminButton;

    Stage dialogStage = new Stage();
    Scene scene;

    /**
     * Check if the UsernameField and the PasswordField have been left empty.
     *
     * @return true if they have, false if not
     */
    public boolean fieldsEmpty(){
        if(nameField.getText().equals("") && surnameField.getText().equals("") &&
                usernameField.getText().equals("") && passwordField.getText().equals(""))
            return true;
        else return false;
    }

    /**
     * Reset to empty values the TextField and the PasswordField
     *
     * @param event press on reset button
     */
    @FXML
    void cancel(ActionEvent event) {
        this.nameField.clear();
        this.surnameField.clear();
        this.usernameField.clear();
        this.passwordField.clear();
        this.adminButton.setSelected(false);
    }

    /**
     * Back to Login page.
     *
     * @param event press on Back to Login button
     */
    @FXML
    void openLogin(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Login/Login.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Insert a new Member or Administrator in the database.
     * @param event press on register when all the fields have been filled.
     */
    @FXML
    void register(ActionEvent event) {
        if(!fieldsEmpty()){
            String name = nameField.getText();
            String surname = surnameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean isAdmin = adminButton.isSelected();
            if(isAdmin){
                Admin admin = new Admin(name, surname, username, password);
                admin.register();
            }
            else {
                Member member = new Member(name, surname, username, password);
                member.register();
            }
            cancel(event);
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

}
