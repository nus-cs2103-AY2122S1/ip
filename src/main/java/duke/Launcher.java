package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to launch Duke.
     * @param args
     */
    public static void main(String[] args) {
        //new Duke().startCli(); //Method to launch CLI version of duke
        Application.launch(Duke.class, args); //Method to launch GUI version of duke
    }
}
