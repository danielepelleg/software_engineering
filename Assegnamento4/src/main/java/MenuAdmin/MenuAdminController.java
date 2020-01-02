package MenuAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MenuAdminController Class
 * Controls the AdminMenu.fxml events.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class MenuAdminController {

    // TODO Insert right images and complete AdminMenu.fxml with missing admin functions

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Button courseButton;

    @FXML
    private Button raceButton;

    @FXML
    private Button subscribersButton;

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    void openCoursePage(ActionEvent event) {
        //TODO CourseAdmin.fxml and Controller Class. Add/Remove methods
    }

    @FXML
    void openRacePage(ActionEvent event) {
        //TODO RaceAdmin.fxml and Controller Class. Add/Remove methods
    }

    @FXML
    void openSubscribersPage(ActionEvent event) {
        //TODO Subscriber.fxml and Controller Class. (Same as Member?)
    }

    /**
     * Logout the administrator, open the login main page.
     *
     * @param event press on Logout button
     */
    @FXML
    void logout(ActionEvent event){
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
}
