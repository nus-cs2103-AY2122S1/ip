package ui;

import javafx.application.Application;

/**
 * A class for easier usage of the Main class.
 */
public final class Launcher {

    public static void main(String[] args) {
        new Launcher().launch();
    }

    /**
     * Launches the GUI for Duke implemented in Main and other classes.
     */
    public void launch() {
        Application.launch(Main.class, "");
    }
}
