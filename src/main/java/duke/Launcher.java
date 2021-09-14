package duke;
import javafx.application.Application;

import java.util.Arrays;
import java.util.List;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        // Adapted from https://stackoverflow.com/a/1254338
        // Allows user to switch between gui and console mode.
        final List<String> arguments = Arrays.asList(args);
        final int modeIndex = arguments.indexOf("-mode");
        final String mode = modeIndex == -1 ? "gui" : arguments.get(modeIndex + 1);
        if ("console".equalsIgnoreCase(mode)) {
            runConsole();
        } else if ("gui".equalsIgnoreCase(mode)) {
            Application.launch(Main.class, args);
        } else {
            System.err.println("Bad mode: " + mode);
        }
    }

    /**
     * Run the console version of duke.
     */
    private static void runConsole() {
        Duke duke = new Duke("data/duke.txt", false);
        duke.run();
    }
}
