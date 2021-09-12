package duke;

import java.util.Arrays;
import java.util.List;

import duke.exceptions.AuguryException;
import javafx.application.Application;

/**
 * The {@code Launcher} class is the entry point of Augury.
 * If running using the command line, use the flag
 * "-mode console" to run the application in the console.
 * Otherwise, Augury defaults to "-mode gui".
 */
public class Launcher {

    /**
     * Starts {@code Augury}.
     *
     * @param args Input parameters for Augury.
     */
    public static void main(String[] args) throws AuguryException {
        // Code gleaned from https://stackoverflow.com/a/1254338
        final List<String> arguments = Arrays.asList(args);
        final int modeIndex = arguments.indexOf("-mode");
        final String mode = modeIndex == -1 ? "gui" : arguments.get(modeIndex + 1);
        if (mode.equalsIgnoreCase("console")) {
            runConsole();
        } else if (mode.equalsIgnoreCase("gui")) {
            Application.launch(App.class, args);
        } else {
            System.err.println("Incorrect mode specified (should be one of 'gui' or 'console').");
        }
    }

    /**
     * Initializes the {@code Augury} application in console mode.
     */
    private static void runConsole() throws AuguryException {
        Augury a = new Augury("data");
        a.init();
        a.greet();
        a.loop();
    }
}
