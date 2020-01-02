package MenuMember;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Subscription Class
 * This class is used to display the data about the subscription to an activity of a member
 * in the TableView on RaceMember.fxml and Race.fxml
 */
public class Subscription {
    private final StringProperty activityName;
    private final StringProperty subscribed;

    /**
     * Class Constructor
     *
     * @param activityName the name of the activity
     * @param subscribed a string that return "YES" if the member is subscribed to the activity, "NO" otherwise.
     */
    public Subscription(String activityName, String subscribed) {
        this.activityName = new SimpleStringProperty(activityName);
        this.subscribed = new SimpleStringProperty(subscribed);
    }

    /**
     * Class Getters - Very important for the functionality of the table view.
     *
     * @return String
     */
    public String getActivityName() {
        return activityName.get();
    }

    public String getSubscribed() {
        return subscribed.get();
    }

    /**
     * Class Setters
     */
    public void setActivityName(String activityName) {
        this.activityName.set(activityName);
    }

    public void setSubscribed(String subscribed) {
        this.subscribed.set(subscribed);
    }

    /**
     * Class Getters - Very important for the functionality of the table view.
     *
     * @return StringProperty
     */

    public StringProperty activityNameProperty() {
        return activityName;
    }

    public StringProperty subscribedProperty() {
        return subscribed;
    }

};