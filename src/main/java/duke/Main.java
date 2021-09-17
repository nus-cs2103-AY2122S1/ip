package duke;

import java.io.IOException;

import duke.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author Tan Yi Guan
 */
public class Main extends Application {

    private final Duke duke = new Duke("./data/task.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().printMessage("Hello, I'm Duke.\nHow may I help you?");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
