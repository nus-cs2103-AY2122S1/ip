package banana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Sets the stage of the program.
 */
public class Main extends Application {

    private Duke duke = new Duke(
            "/Users/ravi57004/ip/src/main/java/Tasks.txt");

    /**
     * Loads the FXMLLoader object
     * and sets the stage title.
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                    "/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Banana");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}