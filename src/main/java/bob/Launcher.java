package bob;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Bob GUI.
     *
     * @param args String array that acts as the argument to the main method.
     */
    public static void main(String[] args) {
        Application.launch(Bob.class, args);
    }
}
