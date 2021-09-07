package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert Duke.class != null : "There should be a Duke class inside the project folder";
        Application.launch(Duke.class, args);
    }
}