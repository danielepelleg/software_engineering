package AlertBox;

import javafx.scene.control.Alert;

/**
 * Warning Box Class
 * Show an alert that warns the user about something.
 */
public class WarningBox {

    /**
     * Class Constructor
     *
     * @param infoMessage the description about the warning given
     * @param titleBar the title of the warning
     */
    public WarningBox(String infoMessage, String titleBar) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}