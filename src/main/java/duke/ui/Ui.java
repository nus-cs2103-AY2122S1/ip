package duke.ui;

import java.io.IOException;

import duke.Duke;
import duke.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class is an abstraction of the Ui of the application.
 */
public class Ui {

    /**
     * Loads the JavaFX components
     *
     * @param primaryStage JavaFX stage
     * @param duke Duke instance that handles the logic
     */
    public void start(Stage primaryStage, Duke duke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
