package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches duke
     * @param args
     */
    public static void main(String[] args) {
        //new Duke().startCli(); // Method for launching the CLI version
        Application.launch(Duke.class, args); // Method for launching the GUI version
    }
}
