package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The method that is responsible for launching the application.
     * @param args the variable of the main method
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
