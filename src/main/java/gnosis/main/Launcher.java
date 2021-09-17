package gnosis.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches Gnosis application.
     *
     * @param args command interface to take.
     */
    public static void main(String[] args) {
        Application.launch(Gnosis.class, args);
    }
}
