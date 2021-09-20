package duke;

import duke.ui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues. This is the entry point to the program.
 */
public class Launcher {

    /**
     * Launches the application.
     *
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
