package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class encapsulates the main class that starts Duke with a GUI.
 */
public class Main extends Application {

    Duke currDuke = Duke.getCurrDuke();


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(currDuke);
            stage.show();
            Duke.dukeStarter(currDuke);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
