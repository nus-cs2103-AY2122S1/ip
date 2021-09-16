package duke;

import static javafx.application.Application.launch;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main method for launch the Main class
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }
}