package Activities;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activities {

    private final StringProperty activityName;
    private final StringProperty activityType;


    public Activities(String activityName, String activityType) {
        this.activityName = new SimpleStringProperty(activityName);
        this.activityType = new SimpleStringProperty(activityType);
    }

    public String getActivityName() {
        return activityName.get();
    }

    public StringProperty activityNameProperty() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName.set(activityName);
    }

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
