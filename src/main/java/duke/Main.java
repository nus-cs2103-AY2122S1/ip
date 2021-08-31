package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        new Duke(stage);
    }
}
