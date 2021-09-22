package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//Class code reused from JavaFX tutorial of this module https://se-education.org/guides/tutorials/javaFx.html
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}