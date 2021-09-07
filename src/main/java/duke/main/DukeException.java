package duke.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents exceptions raised in Duke.Duke.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2.
 */
public class DukeException extends Exception {
    private Exception exception;

    /**
     * Class constructor.
     *
     * @param exception the exception thrown.
     */
    public DukeException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Returns a message notifying user of an error.
     *
     * @return error message.
     */
    @Override
    public String getMessage() {
        String message;
        if (exception instanceof StringIndexOutOfBoundsException
            || exception instanceof ArrayIndexOutOfBoundsException) {
            message = "☹ The duke.task description or duke.command is incomplete.:$\n";
        } else if (
            exception instanceof IndexOutOfBoundsException) {
            message = "☹ OH NO!!! The duke.task does not exist.>:(\n";
        } else if (exception instanceof IOException) {
            message = "☹ OH NO!!! I cannot find the file.\n";
        } else if (exception instanceof DateTimeParseException) {
            message = "Please enter a proper date and time format.";
        } else {
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        return message;
    }
}
