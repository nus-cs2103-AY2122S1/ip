package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method for Launcher class.
     *
     * @param args array of string arguments supplied.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
