package duke;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the programme.
     *
     * @param args default.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
