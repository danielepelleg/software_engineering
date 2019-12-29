package MenuMember;

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

public class MenuMemberController implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button courseButton;

    @FXML
    private Button raceButton;

    @FXML
    private Button subscribersButton;

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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        setLabels();
    }

    @FXML
    void openCoursePage(ActionEvent event) {

    }

    @FXML
    void openRacePage(ActionEvent event) {

    }

    @FXML
    void openSubscribersPage(ActionEvent event) {

    }

    /**
     * Logout the member
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
