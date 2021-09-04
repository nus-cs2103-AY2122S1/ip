package aisu.ui.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Liaw Xin Yan
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
