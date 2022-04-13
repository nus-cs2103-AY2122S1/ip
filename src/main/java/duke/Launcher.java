package duke;

import javafx.application.Application;

/**
 * Resolves classpath issues as a workaround.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
