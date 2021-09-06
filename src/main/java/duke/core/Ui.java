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
     * Greets user when the user starts up the program.
     */
    public String greetUser() {
        return "Hello! I'm Duke!\nHow may I be of service to you?";
    }

    public String showError(DukeException e) {
        return e.getMessage();
    }
}
