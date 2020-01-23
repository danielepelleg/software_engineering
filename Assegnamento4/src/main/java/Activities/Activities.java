package Activities;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Activities Class
 * This class is used to display the data about an activity
 * in the TableView on Activities.fxml
 *
 */
public class Activities {

    private final StringProperty activityName;
    private final StringProperty activityType;


    public Activities(String activityName, String activityType) {
        this.activityName = new SimpleStringProperty(activityName);
        this.activityType = new SimpleStringProperty(activityType);
    }

    /**
     * Class Getters - Very important for the functionality of the table view.
     *
     * @return String
     */
    public String getActivityName() {
        return activityName.get();
    }

    public StringProperty activityNameProperty() {
        return activityName;
    }

    /**
     * Class Setters
     */
    public void setActivityName(String activityName) {
        this.activityName.set(activityName);
    }

    /**
     * Class Getters - Very important for the functionality of the table view.
     *
     * @return StringProperty
     */

    public String getActivityType() {
        return activityType.get();
    }

    public StringProperty activityTypeProperty() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType.set(activityType);
    }

}
