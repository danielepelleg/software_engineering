package MenuAdmin;

import SportClub.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    private Button activitiesButton;

    Stage dialogStage = new Stage();
    Scene scene;


    /**
     * Set the text of the labels.
     */
    void setLabels(){
        nameLabel.setText(Session.getCurrentSession().getName());
        surnameLabel.setText(Session.getCurrentSession().getSurname());
        usernameLabel.setText(Session.getCurrentSession().getUsername());
    }

    /**
     * Open the Course Subscription - Unsubscription page.
     *
     * @param event press on Course Button
     */
    @FXML
    void openCoursePage(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Course/CourseAdmin.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Open the Race Subscription - Unsubscription page.
     *
     * @param event press on Race Button
     */
    @FXML
    void openRacePage(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Race/RaceAdmin.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void openSubscribersPage(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Users/Users.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void openActivitiesPage(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Activities/Activities.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
