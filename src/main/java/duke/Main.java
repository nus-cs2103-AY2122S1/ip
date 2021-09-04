package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MainDukeStarter extends Application {


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        Duke currDuke = Duke.getCurrDuke();
        Duke.dukeStarter(currDuke);

        Scene mainWindow = new Scene()
    }
}
