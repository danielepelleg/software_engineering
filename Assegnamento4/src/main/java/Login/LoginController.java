package Login;

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

    }

}
