package duke;

import java.io.IOException;
import java.nio.file.Paths;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for WorkDone using FXML.
 */
public class Main extends Application {

    private final String filePath = System.getProperty("user.dir");
    private final WorkDone WorkDone = new WorkDone(Paths.get(filePath, "data", "duke1.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(WorkDone);
            stage.show();
            fxmlLoader.<MainWindow>getController().greetTheUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
