package Login;
import AlertBox.ErrorBox;
import AlertBox.InfoBox;
import Database.*;

import AlertBox.WarningBox;
import SportClub.Member;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void openAdminMenu(ActionEvent event){
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
     * Open the registration page.
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
            Member member = new Member(username, password);
            boolean isAdmin = adminButton.isSelected();
            boolean authentication = DatabaseManager.authenticate(member.getUsername(), member.getPassword(), isAdmin);
            if(authentication) {
                new InfoBox("Login Successfull", "Success");
                if (adminButton.isSelected())
                    openAdminMenu(event);
                else openMemberMenu(event);
            }
            else{
                new ErrorBox("Username or password are incorrect", "Bad Login");
                cancel(event);
            }
        }
    }

}
