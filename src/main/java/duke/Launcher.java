package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Toh Wang Bin, with reference to SE-EDU
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }

}
