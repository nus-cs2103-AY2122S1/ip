package duke.ui;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            duke = new Duke(fxmlLoader.<MainWindow>getController());
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            duke.boot();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
