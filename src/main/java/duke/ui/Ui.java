package duke.ui;

import duke.exceptions.InvalidActionException;
import duke.exceptions.InvalidTaskCreationException;
import duke.exceptions.UnknownCommandException;

/**
 * The {@code Ui} class handles interfacing with the user.
 */
public class Ui {
    public Ui() {  }

    /**
     * Prints a string in a specific response format.
     *
     * @param message {@code String} to be printed.
     */
    public void speak(String message) {
        System.out.println("\t_________________________________");
        System.out.println("\t " + message);
        System.out.println("\t_________________________________");
    }
}
