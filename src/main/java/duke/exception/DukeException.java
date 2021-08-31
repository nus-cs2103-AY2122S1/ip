package duke.exception;

import java.util.List;
import java.util.ArrayList;

/**
 * The abstract DukeException class encapsulates information
 * and methods pertaining to exceptions in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public abstract class DukeException extends IllegalArgumentException {
    /**
     * Creates and initalizes a new DukeException with the given message.
     *
     * @param message The error message to be displayed.
     * @return A new DukeException object.
     */
    public DukeException(String message) {
        super("Duke has encountered an problem: " + message);
    }

    /**
     * Returns the formatted lines of messages that should be
     * displayed to a user when encountering this exception.
     *
     * @return A List of Strings that should be output as help messages.
     */
    public List<String> getHelpMessages() {
        return new ArrayList<>();
    }
}