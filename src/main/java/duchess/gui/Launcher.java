package duchess.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Drives the GUI code.
     * @param args The arguments from the System.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
