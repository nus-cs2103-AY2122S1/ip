package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the entire app.
     * @param args String[] of inputs to start
     * running the application.
     */
    public static void main(String[] args) {
        Application.launch(LaunchDuke.class, args);
    }
}