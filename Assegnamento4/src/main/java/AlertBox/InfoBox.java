package AlertBox;

import javafx.scene.control.Alert;

/**
 * Information Box Class
 * Show an alert that informs the user about something.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class InfoBox {

    /**
     * Class Constructor
     *
     * @param infoMessage the description about the information given
     * @param titleBar the title of the information
     */
    public InfoBox(String infoMessage, String titleBar) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}