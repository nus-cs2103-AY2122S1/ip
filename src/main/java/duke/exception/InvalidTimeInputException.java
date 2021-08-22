package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
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

