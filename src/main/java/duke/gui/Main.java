package duke.gui;

import java.io.IOException;

import duke.Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents the GUI for Duke using FXML.
 */
public class Main extends Application {
    /**
     * Represents the duke object.
     */
    private Duke duke = new Duke();

    /**
     * Starts the GUI
     *
     * @param stage Stage which we want to set the scene on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getRoot().setStyle("-fx-font-family: 'Arial'"); //fix
            stage.setTitle("StuDo");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
