package meow;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Start of the application.
     *
     * @param args The given args.
     */
    public static void main(String[] args) {
        Application.launch(meow.Main.class, args);
    }

}
