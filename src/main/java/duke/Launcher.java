package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches Duke GUI.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
