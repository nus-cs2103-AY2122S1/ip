package duke;

import java.io.IOException;

import duke.components.MainWindow;
import duke.storage.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke(Storage.DEFAULT_FILE_PATH);

    /**
     * Starts Application
     * @param stage to start application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = getFxmlLoader();
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            setDukeInstance(fxmlLoader);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDukeInstance(FXMLLoader fxmlLoader) {
        fxmlLoader.<MainWindow>getController().setDuke(duke);
    }

    private FXMLLoader getFxmlLoader() {
        return new FXMLLoader(Main.class.getResource(MainWindow.FXML_STRING_PATH));
    }
}
