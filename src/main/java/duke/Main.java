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
    private final String pathName = "./data/duke.txt";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nee – Pocket Task Manager");
            fxmlLoader.<MainWindow>getController().setDuke(new Duke(pathName, true));
            scene.getStylesheets().add("/styles/styles.css");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
