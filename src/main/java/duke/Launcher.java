package duke;

import javafx.application.Application;

/**
 * Encapsulates a launcher with a main function that is the entrypoint of the application.
 */
public class Launcher {
    /**
     * Launches the application.
     *
     * @param args Vararg.
     */
    public static void main(String ...args) {
        Application.launch(Main.class, args);
    }
}
