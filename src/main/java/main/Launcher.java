package main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Starts the GUI application.
     *
     * @param args empty as it is the main method as required by javaFX.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
