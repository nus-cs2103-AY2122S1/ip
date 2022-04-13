package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * This is the entry point for the Duke program.
     *
     * @param args An array of String arguments to follow convention.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
