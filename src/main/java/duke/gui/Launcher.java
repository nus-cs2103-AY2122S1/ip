package duke.gui;

import duke.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classhpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
