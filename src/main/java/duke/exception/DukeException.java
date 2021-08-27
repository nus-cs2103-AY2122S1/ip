package duke.exception;

import duke.Duke;
import duke.io.TextColor;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Clears any queued message and prints the error message in red instead
     */
    public void displayError() {
        Duke.getUi().resetMessage();
        Duke.getUi().addMessage("Oops! Something went wrong :(\n", TextColor.DEFAULT);
        Duke.getUi().addMessage(getMessage(), TextColor.RED);
    }
}
