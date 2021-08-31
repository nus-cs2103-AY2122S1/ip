import javafx.application.Application;

import kayu.Kayu;
import ui.Launcher;

/**
 * Drives the whole program.
 */
public class Main {

    /**
     * Driver function for main logic using {@link kayu.Kayu}.
     *
     * @param args Command line arguments fed.
     */
    public static void main(String[] args) {
        // Kayu kayu = new Kayu();
        // kayu.runProgram();
        // System.exit(0);
        Application.launch(Launcher.class, args);
    }
}
