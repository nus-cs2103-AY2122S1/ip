package Duke;

import Duke.Duke;
import javafx.application.Application;

import static javafx.application.Application.launch;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        launch(Duke.class, args);
    }
}