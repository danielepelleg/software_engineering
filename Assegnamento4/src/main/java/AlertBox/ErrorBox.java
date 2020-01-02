package AlertBox;

import javafx.scene.control.Alert;

/**
 * Error Box Class
 * Show an alert that notify the user about an error.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class ErrorBox{

    /**
     * Class Constructor
     *
     * @param infoMessage the description about the error given
     * @param titleBar the title of the error
     */
    public ErrorBox(String infoMessage, String titleBar) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}