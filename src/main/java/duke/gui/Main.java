package duke.gui;

import static duke.Duke.FILE;
import static duke.Duke.OUTER_DIR;

import java.io.IOException;
import java.nio.file.Paths;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke(Paths.get(OUTER_DIR, FILE));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getRoot().setStyle("-fx-font-family: 'Arial'");
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().welcomeuser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
