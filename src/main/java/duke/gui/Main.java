package duke.gui;

import duke.processor.Duke;
import duke.processor.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * class to store method to run duke
 * Resuing code of javaFX tutorial from SE-EDU
 */
public class Main extends Application {

    private Duke duke = new Duke(Storage.load());

    /**
     * start duke
     *
     * @param stage system parameter for javaFX
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            stage.setResizable(false);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            assert 1 == 0 : e.toString();
        }
    }
}