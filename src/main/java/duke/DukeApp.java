package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class DukeApp extends Application {
    private static final String DUKE_UI_FXML_PATH = "/view/DukeUi.fxml";
    private static final String TITLE = "Duke";
    private static final boolean IS_RESIZABLE = false;

    /**
     * Initializes Stage and starts GUI.
     *
     * @param stage Stage of GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeApp.class.getResource(DUKE_UI_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            stage.setResizable(IS_RESIZABLE);
            stage.show();
        } catch (IOException e) {
            System.out.println("Duke UI fxml file not found: " + e.getMessage());
        }
    }
}
