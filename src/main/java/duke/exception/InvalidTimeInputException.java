package duke.exception;

/**
 * The InvalidTimeInputException is thrown when user gives a time that is not valid or in an acceptable form.
 *
 * @author Benedict Chua
 */
public class InvalidTimeInputException extends DukeException {
    /**
     * Constructor for InvalidTimeInputException.
     *
     * @param timeString String containing the time inputted in a wrong format by the user.
     */
    public InvalidTimeInputException(String timeString) {
        super("BAKA! I don't understand this Time input!\n"
                + String.format("Time: %s\n", timeString)
                + "It should be a valid time in the form HH:MM or HHMM!");
    }
}

