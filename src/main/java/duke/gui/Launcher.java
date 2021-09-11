package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Runs a new Launcher instance.
     *
     * @param args Provided arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
