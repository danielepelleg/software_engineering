package MenuAdmin;

import SportClub.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class MenuAdminController implements Initializable {

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
    private Button usersButton;

    @FXML
    private Button activitiesButton;

    @FXML
    private Button subscribersButton;

    Stage dialogStage = new Stage();

    Scene scene;

    /**
     * Initialize the page
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        setLabels();
    }

    /**
     * Set the text of the labels.
     */
    void setLabels(){
        nameLabel.setText("Name: " + Session.getAdminSession().getName());
        surnameLabel.setText("Surname: " + Session.getAdminSession().getSurname());
        usernameLabel.setText("Username: " + Session.getAdminSession().getUsername());
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

    /**
     * Open the Users Information Page
     *
     * @param event press on Users Button
     */
    @FXML
    void openUsersPage(ActionEvent event) {
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

    /**
     * Open the Activities Information Page
     *
     * @param event press on Activities Button
     */
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
     * Open the numbers of subscribers page
     * @param event press on Subscribers Button
     */
    @FXML
    void openSubscribersPage(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../Subscribers/Subscribers.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e) {
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
            Session.clearSession();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
