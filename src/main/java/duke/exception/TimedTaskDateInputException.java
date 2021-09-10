package duke.exception;

public class TimedTaskDateInputException extends DukeException {
    /**
     * The constructor method for DukeException.
     *
     */
    public TimedTaskDateInputException() {
        super("Incorrect form of date input. Please try yyyy-MM-dd HH:mm instead.");
    }
}
