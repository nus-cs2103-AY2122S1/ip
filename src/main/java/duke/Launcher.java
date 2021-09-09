package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
//        assert false;
//        if (true)
//            throw new AssertionError();
        Application.launch(Main.class, args);
    }
}