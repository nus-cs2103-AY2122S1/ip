package duke.exception;

/**
 * The InvalidTimeInputException is thrown when user gives a time that is not valid or in an acceptable form.
 *
 * @author Benedict Chua
 */
public class InvalidTimeInputException extends DukeException {
    public InvalidTimeInputException(String timeString) {
        super("BAKA! I don't understand this Time input!\n"
                + String.format("     Time: %s\n", timeString)
                + "     It should be a valid time in the form HH:MM or HHMM!");
    }
}

