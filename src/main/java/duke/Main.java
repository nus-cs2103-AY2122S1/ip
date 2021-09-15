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

    private Duke duke;

    @Override
    public void start(Stage stage) {
        MainWindow ap = new MainWindow();
        duke = new Duke(stage);
        ap.setDuke(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}
