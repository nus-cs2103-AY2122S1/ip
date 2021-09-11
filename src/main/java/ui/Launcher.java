package ui;

import javafx.application.Application;

/**
 * A class for easier usage of the Main class.
 */
public final class Launcher {

    public static void main(String[] args) {
        new Launcher().launch();
    }

    public void launch() {
        Application.launch(Main.class, "");
    }
}
