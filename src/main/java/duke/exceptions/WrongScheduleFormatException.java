package duke.exceptions;

/**
 * Class of exception that handles
 * invalid schedule format.
 */
public class WrongScheduleFormatException extends DukeException {

    /**
     * Throws a standard exception when user inputs
     * a format that cannot be processed
     * for schedule command.
     */
    public WrongScheduleFormatException() {
        super("Schedule command should only have 1 date input, nothing more or less!");
    }
}
