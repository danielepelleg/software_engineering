package Activities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ActivitiesController {

    @FXML
    private Button subscribeButton;

    @FXML
    private ComboBox<?> userComboBox;

    @FXML
    private Button unsubscribeButton;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private Button loadButton;

    @FXML
    private Button menuButton;

    @FXML
    private TableView<?> subscriptionTable;

    @FXML
    private TableColumn<?, ?> courseColumn;

    @FXML
    private TableColumn<?, ?> subscriptionColumn;

    @FXML
    void backMenu(ActionEvent event) {

    }

    @FXML
    void loadSubscriptionData(ActionEvent event) {

    }

    @FXML
    void subscribeMember(ActionEvent event) {

    }

    @FXML
    void unsubscribeMember(ActionEvent event) {

    }
}
