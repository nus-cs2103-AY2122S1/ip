package agent;

import javafx.application.Application;

/**
 * Main class used to launch the Duke chat bot and its GUI.
 */
public class Launcher {

    /**
     * Main method which launches the Duke chat bot and its GUI.
     *
     * @param args accepts a single String argument for image file name placed in
     *             /resources/images for use a background picture.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
