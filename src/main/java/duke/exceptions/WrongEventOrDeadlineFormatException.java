package duke.exceptions;

/**
 * Class that handles event or deadline command without
 * a date or time stated.
 */
public class WrongEventOrDeadlineFormatException extends DukeException {

    /**
     * Throws standard error message when event or deadline has
     * date or time not specified.
     */
    public WrongEventOrDeadlineFormatException() {
        super("Event or Deadline task requires a date and time input!");
    }
}
