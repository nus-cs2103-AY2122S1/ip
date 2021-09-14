package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Drives the Duke JavaFx program.
     *
     * @param args Command-line inputs by the user.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}