package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import duke.controller.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Image dukeImage =
                    new Image(getClass().getResourceAsStream("../images/GivePLZTransparent.png"));
            fxmlLoader.setRoot(new MainWindow());
            stage = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(dukeImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
