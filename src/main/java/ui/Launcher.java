package ui;

import javafx.application.Application;

/**
 * A class for easier usage of DukeApp.
 */
public final class Launcher {

    public static void main(String[] args) {
        run();
    }

    /**
     * Starts the DukeApp.
     */
    public static void run() {
        Application.launch(DukeApp.class, "");
    }
}
