package duke;

import duke.ui.console.ConsoleApplication;
import duke.ui.graphical.GraphicalApplication;
import javafx.application.Application;

/**
 * A launcher class to launch Duke.
 */
public class Launcher {
    /**
     * Launches Duke either in GUI mode or console mode.
     *
     * @param args Arguments to the launcher.
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--console")) {
            ConsoleApplication.start();
        } else {
            Application.launch(GraphicalApplication.class, args);
        }
    }

}
