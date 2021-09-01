package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow();
        mw.setDuke(duke);
        Scene scene = new Scene(mw);
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.show();
    }
}