package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Provided by the guide from "https://se-education.org/guides/tutorials/javaFxPart1.html"
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
