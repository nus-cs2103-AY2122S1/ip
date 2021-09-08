package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
