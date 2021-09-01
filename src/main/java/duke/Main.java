package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDuke(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("Duke Agenda Bot");
        stage.show();
    }
}