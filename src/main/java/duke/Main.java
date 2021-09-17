package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Re-factored Main Class to separate Duke Logic form Ui.
 * Code re-used from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {

    private final Duke DUKE = new Duke();

    /**
     * Overrides the Application's start method.
     *
     * @param stage The JavaFX stage to use.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(DUKE);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.setTitle("Duke by Vishnu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
