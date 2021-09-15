package duke;

import duke.Ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The class for Main
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the MainWindow Scene
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            stage.setTitle("Duke-Mark");
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("Stytle.css");
            stage.setScene(scene); // Setting the stage to show our screen
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setMaxHeight(620);
            stage.setMinHeight(620);
            stage.setMaxWidth(400);
            stage.setMinWidth(400);
            stage.show(); // Render the stage.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
