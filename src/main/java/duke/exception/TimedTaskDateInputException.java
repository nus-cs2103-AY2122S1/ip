package duke.exception;

/**
 * An exception to indicate the wrong input of date format, not accepted by Duke.
 */
public class TimedTaskDateInputException extends DukeException {

    public TimedTaskDateInputException() {
        super("Incorrect form of date input. Please try yyyy-MM-dd HH:mm instead.");
    }
}
