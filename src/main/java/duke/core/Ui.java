package duke.core;

import duke.exception.DukeException;

/**
 * Ui encapsulates the user interface of text-based Duke which the user interacts with. It can greet the player
 * and print the results return by the commands.
 *
 * @author Clifford
 */
public class Ui {
    /**
     * Greets User when the user starts up application.
     *
     * @return the greeting message by Duke.
     */
    public String greetUser() {
        return "Hello! I'm Duke!\nHow may I be of service to you?";
    }

    /**
     * Shows the Error from the user interaction with Duke.
     * @param e the Exception that was thrown
     * @return the exception message on why it was thrown
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }
}
