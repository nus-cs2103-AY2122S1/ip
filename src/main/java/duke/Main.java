package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private GUI gui = new GUI();

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert fxmlLoader.<MainWindow>getController() instanceof MainWindow;
            fxmlLoader.<MainWindow>getController().setGUI(gui);
            stage.show();
            fxmlLoader.<MainWindow>getController().welcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}