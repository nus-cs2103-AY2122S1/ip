package duke;

import javafx.application.Application;

public class DukeLauncher {

    /**
     * Launches Duke application.
     *
     * @param args commandline arguments.
     */
    public static void main(String[] args) {
        // Launches standalone Duke application by calling start method in Main.class.
        // Does not return until application has exited.
        Application.launch(Main.class, args);
    }
}
