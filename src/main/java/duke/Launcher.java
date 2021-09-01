package duke;

import javafx.application.Application;

/**
 * A launcher class to work around class path issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
