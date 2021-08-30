package duke.exception;

import duke.ui.Ui;

/**
 * This exception is thrown when
 * user inputs a non-existent task number
 * when executing deletion or marking of tasks.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class NoSuchTaskException extends DukeException {
    /**
     * Constructor for an NoSuchCommandException.
     *
     * @param ui Prints message with respect to user input.
     */
    public NoSuchTaskException(Ui ui) {
        super(ui);
    }

    /**
     * Returns an error message to highlight that
     * the input task number does not exist.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The task does not exist.");
    }
}
