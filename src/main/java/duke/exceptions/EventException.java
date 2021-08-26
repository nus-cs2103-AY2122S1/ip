package duke.exceptions;

import duke.exceptions.DukeException1;
/**
 * Exception that is thrown when an Event class cannot be created due to empty description provided by user.
 */
public class EventException extends DukeException1 {
    /**
     * Creates an object of the EventException class.
     */
    public EventException() {
        super();
    }

    /**
     * Gets the message to be printed in response to the error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a event cannot be empty.";
    }
}
