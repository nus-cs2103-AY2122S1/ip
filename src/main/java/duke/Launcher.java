package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//@@author Jeffry Lum-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
