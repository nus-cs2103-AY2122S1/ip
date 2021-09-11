package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author tanhuakun-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            stage.show();
            mainWindow.showDukeMessage(duke.getWelcome());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author