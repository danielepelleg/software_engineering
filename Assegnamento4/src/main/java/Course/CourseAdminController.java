package Course;

import AlertBox.WarningBox;
import Database.DatabaseManager;
import MenuMember.Subscription;
import SportClub.Admin;
import SportClub.Course;
import SportClub.Member;
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

public class CourseAdminController implements Initializable {

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
    private TableColumn<Subscription, String> courseColumn;

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
        setComboBox();
        setUserComboBox();
    }


    /**
     * Set the text of the labels.
     */
    private void setLabels(){

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
     * Set the options of the userComboBox.
     */
    private void setUserComboBox(){
        try
        {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT sportclub.member.username FROM sportclub.member");
            ResultSet rs = pstmt.executeQuery();
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
        try {
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
                    ("SELECT activity_course.course_name FROM activity_course WHERE activity_course.member_username = ?");
            pstmt.setString(1, userComboBox.getValue());
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
        String userSelected = userComboBox.getValue();
        if (courseSelected != null && userSelected != null) {
            new Admin().subscribe(new Course(courseSelected), DatabaseManager.getSelectedMember(userSelected));
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
        String userSelected = userComboBox.getValue();
        if (courseSelected != null && userSelected != null) {
            new Admin().unsubscribe(new Course(courseSelected), DatabaseManager.getSelectedMember(userSelected));
            loadData();
        }
        else new WarningBox("You have to choose a course!", "Information Missing");
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
