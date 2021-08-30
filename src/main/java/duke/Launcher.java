package duke;

import javafx.application.Application;

/**
 * A Launcher class to workaround classpath issues. args argument is ignored.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
