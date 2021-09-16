package lifeline;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class starts the program.
 */
public class Main extends Application {

    /**
     * Starts the GUI program.
     *
     * @param stage Main stage which represents primary window of JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("styles/lifeline.css");

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Lifeline");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
