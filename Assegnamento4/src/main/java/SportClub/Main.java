package SportClub;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SportClub.Main Class
 * Run the UI and open the login page.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Main extends Application {

    private static String UI = "../Login/Login.fxml";

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UI));
        Scene frame = new Scene(root);
        primaryStage.getIcons().add(new Image("/images/racket.png"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("SportClub");
        primaryStage.setScene(frame);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}