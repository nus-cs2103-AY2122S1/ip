package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Taken / adapted from Seedu JavaFX Tutorial Part4
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
