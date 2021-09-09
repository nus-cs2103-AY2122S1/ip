package duke;

import java.io.IOException;
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private GUI gui = new GUI();

    @Override
    public void start(Stage stage) {

        try {
            // FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("src/main/resources/view/MainWindow.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader((new File("src/main/resources/view/MainWindow.fxml")).toURI().toURL());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGUI(gui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}