package Login;

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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LoginController Class
 * Controls the Login.fxml events.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class LoginController {

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

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
        if(usernameField.getText().equals("") && passwordField.getText().equals(""))
            return true;
        else return false;
    }

    /**
     * Open the Member menu page.
     *
     * @param event successful login as member
     */
    public void openMemberMenu(ActionEvent event){
        try {
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuMember/MemberMenu.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the Administrator menu page.
     *
     * @param event successful login as administrator
     */
    public void openAdminMenu(ActionEvent event){
        try {
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuAdmin/AdminMenu.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset to empty values the TextField and the PasswordField
     *
     * @param event press on reset button
     */
    @FXML
    void cancel(ActionEvent event) {
        this.usernameField.clear();
        this.passwordField.clear();
        this.adminButton.setSelected(false);
    }

    /**
     * Open the Registration page.
     *
     * @param event press on registration button
     */
    @FXML
    void register(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Registration/Registration.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sign the user in.
     *
     * @param event press on login button
     */
    @FXML
    void login(ActionEvent event) {
        if(fieldsEmpty())
            new WarningBox("You have left some fields empty!", "Informations Missing");
        else{
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean authentication = false;
            boolean isAdmin = adminButton.isSelected();
            if(isAdmin){
                Admin admin = new Admin(username, password);
                authentication = admin.login();
            }
            else {
                Member member = new Member(username, password);
                authentication = member.login();
            }
            if(authentication) {
                if (adminButton.isSelected())
                    openAdminMenu(event);
                else openMemberMenu(event);
            }
            else
                cancel(event);
        }
    }
}
