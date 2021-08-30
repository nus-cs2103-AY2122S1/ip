package duke.application;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application
     * @param args Command-line arguments (none expected)
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
