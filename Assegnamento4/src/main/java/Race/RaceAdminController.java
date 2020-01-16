package Race;

import AlertBox.WarningBox;
import Database.DatabaseManager;
import MenuMember.Subscription;
import SportClub.Course;
import SportClub.Race;
import SportClub.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * RaceMemberClass Class
 * Controls the RaceMember.fxml events.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class RaceAdminController implements Initializable {

    @FXML
    private ComboBox<String> userComboBox;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button subscribeButton;

    @FXML
    private Button unsubscribeButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button loadButton;

    @FXML
    private TableView<Subscription> subscriptionTable;

    @FXML
    private TableColumn<Subscription, String> raceColumn;

    @FXML
    private TableColumn<Subscription, String> subscriptionColumn;

    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Subscription> data;
    private ObservableList<String> options;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setLabels();
        loadData();
        setComboBox();
        setUserComboBox();
    }


    /**
     * Set the text of the labels.
     */
    private void setLabels(){
        //usernameLabel.setText(Session.getCurrentSession().getUsername());
    }

    /**
     * Set the options of the combobox.
     */
    private void setComboBox(){
        try
        {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT * FROM sportclub.race");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }

        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.comboBox.setItems(null);
        this.comboBox.setItems(this.options);
    }

    /**
     * Set the options of the userComboBox.
     */
    private void setUserComboBox(){
        try
        {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT sportclub.administrator.username FROM sportclub.administrator");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }
            pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT sportclub.member.username FROM sportclub.member");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.userComboBox.setItems(null);
        this.userComboBox.setItems(this.options);
    }

    /**
     * Load he data in the TableView
     */
    private void loadData(){
        try
        {
            this.data = FXCollections.observableArrayList();

            PreparedStatement pstmt;
            pstmt = DatabaseManager.getConnection().prepareStatement("SELECT race.name as Race, " +
                    "CASE WHEN member_username = ? THEN 'YES' else 'NO' END " +
                    "FROM sportclub.race LEFT JOIN sportclub.activity_race on race.name = race_name");
            pstmt.setString(1, Session.getCurrentSession().getUsername());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.data.add(new Subscription(rs.getString(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.raceColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        this.subscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("subscribed"));

        this.subscriptionTable.setItems(null);
        this.subscriptionTable.setItems(this.data);
    }

    /**
     * Subscribe the current Member to the Race chosen
     *
     * @param event press on subscribe button
     */
    @FXML
    private void subscribeMember(ActionEvent event) {
        String raceSelected = comboBox.getValue();
        if (raceSelected != null) {
            Session.getCurrentSession().subscribe(new Race(raceSelected));
            loadData();
        }
        else new WarningBox("You have to choose a race!", "Information Missing");
    }

    /**
     * Unsubscribe the current Member to the Course chosen
     *
     * @param event press on unsubscribe button
     */
    @FXML
    private void unsubscribeMember(ActionEvent event) {
        String raceSelected = comboBox.getValue();
        if (raceSelected != null) {
            Session.getCurrentSession().unsubscribe(new Race(raceSelected));
            loadData();
        }
        else new WarningBox("You have to choose a race!", "Information Missing");
    }

    /**
     * Update the data in the table view
     *
     * @param event press on load button
     */
    @FXML
    private void loadSubscriptionData(ActionEvent event) {
        loadData();
    }

    /**
     * Back to Member menu page.
     *
     * @param event press on Back to Menu button
     */
    @FXML
    void backMenu(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuAdmin/AdminMenu.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
