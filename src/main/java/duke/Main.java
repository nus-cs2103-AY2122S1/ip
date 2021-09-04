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
public class Main extends Application {

    private static final String FILE_PATH = "data/taskLog.txt";

    private Duke duke = new Duke(FILE_PATH);
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            this.fxmlLoader = fxmlLoader;
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            duke.run();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToUser(String message) {
        this.fxmlLoader.<MainWindow>getController().sendMessageToUser(message);
    }
}
