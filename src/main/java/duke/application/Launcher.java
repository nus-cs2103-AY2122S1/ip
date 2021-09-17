package duke.application;

import duke.io.AliasStorage;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application and initializes the any other classes.
     *
     * @param args Command-line arguments (none expected).
     */
    public static void main(String[] args) {
        AliasStorage.load();
        Application.launch(Duke.class, args);
    }
}
