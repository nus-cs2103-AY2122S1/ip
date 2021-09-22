package duke;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;
    public static final String APP_NAME = "BTDuke";

    @Override
    public void start(Stage stage) {
        MainWindow ap = new MainWindow();
        duke = new Duke(ap);
        ap.setDuke(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle(APP_NAME);
        stage.show();
        duke.showGreeting();
    }

    @Override
    public void stop(){
        duke.save();
    }
}
