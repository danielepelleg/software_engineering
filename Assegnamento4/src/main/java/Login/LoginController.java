package Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    private boolean isAdmin = adminButton.isSelected();

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }

}
