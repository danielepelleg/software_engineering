package Course;

import AlertBox.WarningBox;
import Database.DatabaseManager;
import Subscribers.Subscription;
import SportClub.Course;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * CourseMemberController Class
 * Controls the CourseMember.fxml events.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class CourseMemberController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button subscribeButton;

    @FXML
    private Button unsubscribeButton;

    @FXML
    private Button menuButton;

    @FXML
    private TableView<Subscription> subscriptionTable;

    @FXML
    private TableColumn<Subscription, String> courseColumn;

    @FXML
    private TableColumn<Subscription, String> subscriptionColumn;

    Stage dialogStage = new Stage();

    Scene scene;

    private ObservableList<Subscription> data;

    private ObservableList<String> options;

    /**
     * Initialize the page.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setLabels();
        loadData();
        setComboBox();
    }

    /**
     * Set the text of the labels.
     */
    private void setLabels(){
        usernameLabel.setText(Session.getMemberSession().getUsername());
    }

    /**
     * Set the options of the combobox.
     */
    private void setComboBox(){
        try
        {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT * FROM sportclub.course");
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
     * Load the data in the TableView
     */
    private void loadData(){
        try
        {
            this.data = FXCollections.observableArrayList();
            PreparedStatement pstmt;
            ArrayList<String> allCourses = new ArrayList<>();
            ArrayList<String> executedCourses = new ArrayList<>();
            pstmt = DatabaseManager.getConnection().prepareStatement("SELECT sportclub.course.name FROM sportclub.course");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                allCourses.add(rs.getString(1));
            }
            pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT AC.course_name FROM activity_course AC WHERE AC.member_username = ?");
            pstmt.setString(1, Session.getMemberSession().getUsername());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                executedCourses.add(rs.getString(1));
            }
            boolean found;
            if(!allCourses.isEmpty()){
                for(String course: allCourses){
                    found = false;
                    if(!executedCourses.isEmpty()){
                        for (String executeCourse: executedCourses){
                            if(course.equals(executeCourse)){
                                this.data.add(new Subscription(course,"YES"));
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            this.data.add(new Subscription(course,"NO"));
                        }
                    }
                    else this.data.add(new Subscription(course,"NO"));
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.courseColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        this.subscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("subscribed"));

        this.subscriptionTable.setItems(null);
        this.subscriptionTable.setItems(this.data);
    }

    /**
     * Subscribe the current Member to the Course chosen
     *
     * @param event press on subscribe button
     */
    @FXML
    private void subscribeMember(ActionEvent event) {
        String courseSelected = comboBox.getValue();
        if (courseSelected != null) {
            Session.getMemberSession().subscribe(new Course(courseSelected));
            loadData();
        }
        else new WarningBox("You have to choose a course!", "Information Missing");
    }

    /**
     * Unsubscribe the current Member to the Course chosen
     *
     * @param event press on unsubscribe button
     */
    @FXML
    private void unsubscribeMember(ActionEvent event) {
        String courseSelected = comboBox.getValue();
        if (courseSelected != null) {
            Session.getMemberSession().unsubscribe(new Course(courseSelected));
            loadData();
        }
        else new WarningBox("You have to choose a course!", "Information Missing");
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
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuMember/MemberMenu.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
