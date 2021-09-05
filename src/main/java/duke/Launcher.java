package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Start of the programme.
     *
     * @param args default.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
